package com.quattrogatti.effective.transaction.data;

import com.quattrogatti.effective.transaction.domain.Amount;
import com.quattrogatti.effective.transaction.domain.Transaction;

import java.util.HashSet;
import java.util.Set;

import static com.quattrogatti.effective.transaction.domain.Transaction.transaction;
import static java.time.LocalDate.now;

public class MongoTransactionRepository implements TransactionRepository {

    @Override
    public Transaction save(Transaction transaction) {
        return transaction;
    }

    @Override
    public Transaction delete(Transaction transaction) {
        return transaction;
    }

    @Override
    public Transaction findOne(String id) {
        return transaction()
                .withAmount(new Amount(10.99))
                .withDescription("A transaction description.")
                .withCreditAccountId("99000099")
                .withDebitAccountId("88811888")
                .withDate(now())
                .build();
    }

    @Override
    public Set<Transaction> findAll() {
        HashSet<Transaction> transactions = new HashSet<>();
        transactions.add(transaction()
                .withAmount(new Amount(10.99))
                .withDescription("A transaction description.")
                .withCreditAccountId("99000099")
                .withDebitAccountId("88811888")
                .withDate(now())
                .build());
        return transactions;
    }

    @Override
    public Set<Transaction> findAll(TransactionSpecification transactionSpecification) {
        HashSet<Transaction> transactions = new HashSet<>();
        transactions.add(transaction()
                .withAmount(new Amount(10.99))
                .withDescription("A transaction description.")
                .withCreditAccountId("99000099")
                .withDebitAccountId("88811888")
                .withDate(now())
                .build());
        return transactions;
    }
}
