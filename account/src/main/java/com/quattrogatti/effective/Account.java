package com.quattrogatti.effective;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

import static java.util.Objects.hash;

@JsonDeserialize(builder = Account.AccountBuilder.class)
public final class Account {

    private final String id;
    private final String name;
    private final String description;
    private final AccountType accountType;

    private Account(AccountBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.accountType = builder.accountType;
    }

    public static AccountBuilder account() {
        return new AccountBuilder();
    }

    public static AccountBuilder from(Account account) {
        return new AccountBuilder(account);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(description, account.description) &&
                Objects.equals(accountType, account.accountType);
    }

    @Override
    public int hashCode() {
        return hash(id, name, description, accountType);
    }

    @JsonIgnoreProperties({"id"})
    public static final class AccountBuilder {

        private String id;
        private String name;
        private String description;
        private AccountType accountType;

        @JsonCreator
        private AccountBuilder() {
            this.accountType = AccountType.INCOME;
        }

        private AccountBuilder(Account account) {
            this.id = account.id;
            this.name = account.name;
            this.description = account.description;
            this.accountType = account.accountType;
        }

        @JsonProperty("description")
        public AccountBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        @JsonProperty("name")
        public AccountBuilder withName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("accountType")
        public AccountBuilder withAccountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public AccountBuilder copy(Account account) {
            this.name = account.name;
            this.description = account.description;
            this.accountType = account.accountType;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
