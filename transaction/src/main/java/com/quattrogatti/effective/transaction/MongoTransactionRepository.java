package com.quattrogatti.effective.transaction;

import java.util.Set;

public class MongoTransactionRepository implements TransactionRepository {

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction delete(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction findOne(String id) {
        return null;
    }

    @Override
    public Set<Transaction> findAll() {
        return null;
    }

    @Override
    public Set<Transaction> findAll(TransactionSpecification transactionSpecification) {
        return null;
    }
}
