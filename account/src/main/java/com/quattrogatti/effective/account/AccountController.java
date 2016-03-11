package com.quattrogatti.effective.account;

import com.quattrogatti.effective.common.RegisteredController;
import org.rapidoid.http.Req;

import java.util.Optional;

import static com.quattrogatti.effective.account.AccountController.AccountParam.ID;
import static com.quattrogatti.effective.account.AccountController.AccountUrl.*;
import static org.rapidoid.http.fast.HttpStatus.NOT_FOUND;
import static org.rapidoid.http.fast.On.*;

public final class AccountController implements RegisteredController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void register() {
        get(ALL).json(req -> getAll());
        get(BY_ID).json(req -> getById(req));
        put(BY_ID).json(req -> putById(req));
        delete(BY_ID).json(req -> deleteById(req));
        post(CREATE).json(req -> postAccount(req));
    }

    public Iterable<Account> getAll() {
        return accountService.find().execute();
    }

    private Account mapAccount(byte[] body) {
        return Account.account().build(); // TODO this should map from json
    }

    private Account getById(Req request) {
        String id = request.param(ID);
        Optional<Account> account = accountService.find().withId(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            request.response().code(NOT_FOUND.ordinal());
            return null;
        }
    }

    private Account putById(Req request) {
        String id = request.param(ID);
        Account account = mapAccount(request.body());
        Optional<Account> fetched = accountService.find().withId(id);
        if (fetched.isPresent()) {
            Account updated = Account.from(fetched.get()).copy(account).build();
            return accountService.with(updated).save();
        }
        request.response().code(NOT_FOUND.ordinal());
        return null;
    }

    private Account deleteById(Req request) {
        String id = request.param(ID);
        Optional<Account> fetched = accountService.find().withId(id);
        if (fetched.isPresent()) {
            accountService.with(fetched.get()).delete();
        }
        request.response().code(NOT_FOUND.ordinal());
        return null;
    }

    private Account postAccount(Req request) {
        Account account = mapAccount(request.body());
        return accountService.with(account).save();
    }

    protected static final class AccountUrl {
        protected static final String ALL = "/accounts";
        protected static final String BY_ID = "/account";
        protected static final String CREATE = "/account";

        private AccountUrl() {
            throw new IllegalArgumentException("Do not instantiate.");
        }
    }

    protected static final class AccountParam {
        protected static final String ID = "id";
        protected static final String DEBIT_ID = "dId";
        protected static final String CREDIT_ID = "cId";
        protected static final String FROM_DATE = "fd";
        protected static final String TO_DATE = "td";

        private AccountParam() {
            throw new IllegalArgumentException("Do not instantiate.");
        }
    }
}
