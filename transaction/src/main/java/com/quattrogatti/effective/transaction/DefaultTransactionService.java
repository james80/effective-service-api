package com.quattrogatti.effective.transaction;

import java.time.LocalDate;
import java.util.Optional;

public class DefaultTransactionService implements TransactionService {

    private TransactionRepository transactionRepository;

    public DefaultTransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionAction with(Transaction t) {
        return new TransactionAction() {
            @Override
            public Transaction save() {
                return transactionRepository.save(t);
            }

            @Override
            public void delete() {
                transactionRepository.delete(t);
            }
        };
    }

    @Override
    public TransactionFinder find() {
        return new TransactionFinder() {

            private final TransactionSpecification transactionSpecification
                    = new TransactionSpecification();

            @Override
            public Optional<Transaction> withId(String id) {
                return Optional.ofNullable(transactionRepository.findOne(id));
            }

            @Override
            public TransactionFinder withDescription(String description) {
                transactionSpecification.setDescriptionParam(description);
                return this;
            }

            @Override
            public TransactionFinder withDebitAccount(Long debitAccount) {
                transactionSpecification.setDebitParam(debitAccount);
                return this;
            }

            @Override
            public TransactionFinder withCreditAccount(Long creditAccount) {
                transactionSpecification.setCreditParam(creditAccount);
                return this;
            }

            @Override
            public TransactionFinder withFromDate(LocalDate date) {
                transactionSpecification.setFromDateParam(date);
                return this;
            }

            @Override
            public TransactionFinder withToDate(LocalDate date) {
                transactionSpecification.setToDateParam(date);
                return this;
            }

            @Override
            public Iterable<Transaction> execute() {
                if (transactionSpecification.hasParams()) {
                    return transactionRepository.findAll(transactionSpecification);
                }
                return transactionRepository.findAll();
            }
        };
    }

}
