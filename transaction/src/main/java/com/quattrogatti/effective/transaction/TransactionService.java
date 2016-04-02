package com.quattrogatti.effective.transaction;

import com.quattrogatti.effective.transaction.data.TransactionRepository;
import com.quattrogatti.effective.transaction.data.TransactionSpecification;
import com.quattrogatti.effective.transaction.domain.Transaction;

import java.time.LocalDate;

public final class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionAction with(Transaction transaction) {
        return new TransactionAction(transaction);
    }

    public final class TransactionAction {

        private final Transaction transaction;

        public TransactionAction(final Transaction transaction) {
            this.transaction = transaction;
        }

        public Transaction save() {
            return transactionRepository.save(transaction);
        }

        public void delete() {
            transactionRepository.delete(transaction);
        }
    }

    public Transaction fetchOne(String id) {
        return transactionRepository.findOne(id);
    }

    public TransactionFinder fetchAll() {
        return new TransactionFinder();
    }

    public final class TransactionFinder {

        private final TransactionSpecification transactionSpecification
                = new TransactionSpecification();

        public TransactionFinder withDebitAccount(String debitAccount) {
            transactionSpecification.withDebitAccountId(debitAccount);
            return this;
        }

        public TransactionFinder withCreditAccount(String creditAccount) {
            transactionSpecification.withCreditAccountId(creditAccount);
            return this;
        }

        public TransactionFinder withFromDate(LocalDate date) {
            transactionSpecification.withFromDate(date);
            return this;
        }

        public TransactionFinder withToDate(LocalDate date) {
            transactionSpecification.withToDate(date);
            return this;
        }

        public Iterable<Transaction> execute() {
            if (transactionSpecification.hasParams()) {
                return transactionRepository.findAll(transactionSpecification);
            }
            return transactionRepository.findAll();
        }
    }
}