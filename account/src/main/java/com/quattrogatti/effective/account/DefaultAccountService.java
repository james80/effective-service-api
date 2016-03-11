package com.quattrogatti.effective.account;

import java.util.Optional;

public class DefaultAccountService implements AccountService {

    private AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountAction with(Account a) {
        return new AccountAction() {
            @Override
            public Account save() {
                return accountRepository.save(a);
            }

            @Override
            public void delete() {
                accountRepository.delete(a);
            }
        };
    }

    @Override
    public AccountFinder find() {
        return new AccountFinder() {
            @Override
            public Optional<Account> withId(String id) {
                return Optional.ofNullable(accountRepository.findOne(id));
            }

            @Override
            public AccountFinder withName(String name) {
                return null;
            }

            @Override
            public AccountFinder withDescription(String description) {
                return null;
            }

            @Override
            public AccountFinder withType(AccountType accountType) {
                return null;
            }

            @Override
            public Iterable<Account> execute() {
                return accountRepository.findAll();
            }
        };
    }
}
