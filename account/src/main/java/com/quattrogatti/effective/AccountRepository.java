package com.quattrogatti.effective;

import java.util.Set;

public interface AccountRepository {
    Account save(Account account);

    Account delete(Account account);

    Account findOne(String id);

    Set<Account> findAll();
}
