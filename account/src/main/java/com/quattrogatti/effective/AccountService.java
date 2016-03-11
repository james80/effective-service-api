package com.quattrogatti.effective;

import java.util.Optional;

public interface AccountService {
    AccountAction with(Account a);

    AccountFinder find();

    interface AccountAction {
        Account save();

        void delete();
    }

    interface AccountFinder {
        Optional<Account> withId(String id);

        AccountFinder withName(String name);

        AccountFinder withDescription(String description);

        AccountFinder withType(AccountType accountType);

        Iterable<Account> execute();
    }
}
