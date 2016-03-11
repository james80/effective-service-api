package com.quattrogatti.effective.transaction;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Optional.of;

public class TransactionSpecification {

    private Optional<String> descriptionParam = Optional.empty();
    private Optional<Long> debitParam = Optional.empty();
    private Optional<Long> creditParam = Optional.empty();
    private Optional<LocalDate> fromDateParam = Optional.empty();
    private Optional<LocalDate> toDateParam = Optional.empty();
    private boolean hasParams = false;

    public void setDescriptionParam(String descriptionParam) {
        this.descriptionParam = of(descriptionParam);
        this.hasParams = true;
    }

    public void setDebitParam(Long debitParam) {
        this.debitParam = of(debitParam);
        this.hasParams = true;
    }

    public void setCreditParam(Long creditParam) {
        this.creditParam = of(creditParam);
        this.hasParams = true;
    }

    public void setFromDateParam(LocalDate fromDateParam) {
        this.fromDateParam = of(fromDateParam);
        this.hasParams = true;
    }

    public void setToDateParam(LocalDate toDateParam) {
        this.toDateParam = of(toDateParam);
        this.hasParams = true;
    }

    public boolean hasParams() {
        return this.hasParams;
    }
}
