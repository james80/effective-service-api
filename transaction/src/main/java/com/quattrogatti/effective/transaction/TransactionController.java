package com.quattrogatti.effective.transaction;

import com.quattrogatti.effective.common.RegisteredController;
import org.rapidoid.http.Req;
import org.rapidoid.http.Resp;

import java.util.Optional;

import static com.quattrogatti.effective.transaction.TransactionController.TransactionParam.*;
import static com.quattrogatti.effective.transaction.TransactionController.TransactionUrl.*;
import static org.rapidoid.http.fast.HttpStatus.NOT_FOUND;
import static org.rapidoid.http.fast.On.*;

public final class TransactionController implements RegisteredController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void register() {
        get(ALL).json(req -> getTransactions(req));
        get(BY_ID).json(req -> getTransaction(req));
        put(BY_ID).json(req -> putTransaction(req));
        delete(BY_ID).json(req -> deleteTransaction(req));
        post(CREATE).json(req -> postTransaction(req));
    }

    private Transaction mapTransaction(byte[] requestBody) {
        return Transaction.transaction().build(); // TODO this should map from json
    }

    private Iterable<Transaction> getTransactions(Req request) {
        // TODO this should be a POJO
        String debitId = request.param(DEBIT_ID);
        String creditId = request.param(CREDIT_ID);
        String fromDate = request.param(FROM_DATE);
        String toDate = request.param(TO_DATE);
        TransactionService.TransactionFinder finder = transactionService.find();
        return finder.execute();
    }

    private Transaction getTransaction(Req request) {
        String id = request.param(ID);
        Optional<Transaction> fetched = transactionService.find().withId(id);
        return fetched.get();
    }

    private Transaction putTransaction(Req request) {
        String id = request.param(ID);
        Optional<Transaction> fetched = transactionService.find().withId(id);
        if (fetched.isPresent()) {
            Transaction updated = Transaction.from(fetched.get()).copy(mapTransaction(request.body())).build();
            return transactionService.with(updated).save();
        }
        return null;
    }

    private Transaction deleteTransaction(Req request) {
        String id = request.param(ID);
        Optional<Transaction> fetched = transactionService.find().withId(id);
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
        protected static final String BY_ID = "/transaction";
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
