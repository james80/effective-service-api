package com.quattrogatti.effective.transaction;

import java.time.LocalDate;
import java.util.Optional;

public interface TransactionService {
    TransactionAction with(Transaction t);

    TransactionFinder find();

    interface TransactionAction {
        Transaction save();

        void delete();
    }

    interface TransactionFinder {
        Optional<Transaction> withId(String id);

        TransactionFinder withDescription(String description);

        TransactionFinder withDebitAccount(Long debitAccountId);

        TransactionFinder withCreditAccount(Long creditAccountId);

        TransactionFinder withFromDate(LocalDate date);

        TransactionFinder withToDate(LocalDate date);

        Iterable<Transaction> execute();
    }
}
