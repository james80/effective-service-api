package com.quattrogatti.effective.transaction;

import java.util.Set;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Transaction delete(Transaction transaction);

    Transaction findOne(String id);

    Set<Transaction> findAll();

    Set<Transaction> findAll(TransactionSpecification transactionSpecification);
}
