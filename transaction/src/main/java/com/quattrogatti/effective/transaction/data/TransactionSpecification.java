package com.quattrogatti.effective.transaction.data;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

public class TransactionSpecification {

    private Optional<String> debit = empty();
    private Optional<String> credit = empty();
    private Optional<LocalDate> from = empty();
    private Optional<LocalDate> to = empty();

    public void withDebitAccountId(String debitAccountId) {
        this.debit = ofNullable(debitAccountId);
    }

    public void withCreditAccountId(String creditAccountId) {
        this.credit = ofNullable(creditAccountId);
    }

    public void withFromDate(LocalDate fromDate) {
        this.from = ofNullable(fromDate);
    }

    public void withToDate(LocalDate toDate) {
        this.to = ofNullable(toDate);
    }

    public boolean hasParams() {
        return this.debit.isPresent()
                || this.credit.isPresent()
                || this.from.isPresent()
                || this.to.isPresent();
    }
}
