package com.quattrogatti.effective.transaction.web;

import com.quattrogatti.effective.common.RegisteredController;
import com.quattrogatti.effective.transaction.TransactionService;
import com.quattrogatti.effective.transaction.TransactionService.TransactionFinder;
import com.quattrogatti.effective.transaction.domain.Transaction;
import org.rapidoid.http.Req;

import java.util.Optional;

import static com.quattrogatti.effective.transaction.web.TransactionController.TransactionParam.*;
import static com.quattrogatti.effective.transaction.web.TransactionController.TransactionUrl.*;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.Optional.ofNullable;
import static org.rapidoid.http.fast.HttpStatus.NOT_FOUND;
import static org.rapidoid.http.fast.On.*;

public final class TransactionController implements RegisteredController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void register() {
        get(ALL).json(this::getTransactions);
        get(BY_ID).json(this::getTransaction);
        put(BY_ID).json(this::putTransaction);
        delete(BY_ID).json(this::deleteTransaction);
        post(CREATE).json(this::postTransaction);
    }

    private Transaction mapTransaction(byte[] requestBody) {
        return Transaction.transaction().build(); // TODO this should map from json
    }

    private Iterable<Transaction> getTransactions(Req request) {
        TransactionFinder finder = transactionService.fetchAll();
        ofNullable(request.param(DEBIT_ID, null)).ifPresent(finder::withDebitAccount);
        ofNullable(request.param(CREDIT_ID, null)).ifPresent(finder::withCreditAccount);
        ofNullable(request.param(FROM_DATE, null))
                .map(date -> parse(date, ISO_LOCAL_DATE))
                .ifPresent(finder::withFromDate);
        ofNullable(request.param(TO_DATE, null))
                .map(date -> parse(date, ISO_LOCAL_DATE))
                .ifPresent(finder::withFromDate);
        return finder.execute();
    }

    private Transaction getTransaction(Req request) {
        String id = request.param(ID);
        return transactionService.fetchOne(id);
    }

    private Transaction putTransaction(Req request) {
        String id = request.param(ID);
        Optional<Transaction> fetched = ofNullable(transactionService.fetchOne(id));
        if (fetched.isPresent()) {
            Transaction updated = Transaction.from(fetched.get()).copy(mapTransaction(request.body())).build();
            return transactionService.with(updated).save();
        }
        return null;
    }

    private Transaction deleteTransaction(Req request) {
        String id = request.param(ID);
        Optional<Transaction> fetched = ofNullable(transactionService.fetchOne(id));
        if (fetched.isPresent()) {
            transactionService.with(fetched.get()).delete();
        }
        request.response().code(NOT_FOUND.ordinal());
        return null;
    }

    private Transaction postTransaction(Req request) {
        return transactionService.with(mapTransaction(request.body())).save();
    }

    protected static final class TransactionUrl {
        protected static final String ALL = "/transactions";
        protected static final String BY_ID = "/transaction/{id}";
        protected static final String CREATE = "/transaction";

        private TransactionUrl() {
            throw new IllegalArgumentException("Do not instantiate.");
        }
    }

    protected static final class TransactionParam {
        protected static final String ID = "id";
        protected static final String DEBIT_ID = "dId";
        protected static final String CREDIT_ID = "cId";
        protected static final String FROM_DATE = "fd";
        protected static final String TO_DATE = "td";

        private TransactionParam() {
            throw new IllegalArgumentException("Do not instantiate.");
        }
    }
}
