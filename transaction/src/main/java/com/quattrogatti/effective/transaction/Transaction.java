package com.quattrogatti.effective.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.quattrogatti.effective.common.LocalDateDeserializer;
import com.quattrogatti.effective.common.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.hash;

@JsonDeserialize(builder = Transaction.TransactionBuilder.class)
public final class Transaction {

    private final String id;
    private final LocalDate date;
    private final String debitAccountId;
    private final String creditAccountId;
    private final String description;
    private final Amount amount;

    private Transaction(TransactionBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.debitAccountId = builder.debitAccountId;
        this.creditAccountId = builder.creditAccountId;
        this.description = builder.description;
        this.amount = builder.amount;
    }

    public static TransactionBuilder transaction() {
        return new TransactionBuilder();
    }

    public static TransactionBuilder from(Transaction transaction) {
        return new TransactionBuilder(transaction);
    }

    public String getId() {
        return id;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate getDate() {
        return date;
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public String getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(debitAccountId, that.debitAccountId) &&
                Objects.equals(creditAccountId, that.creditAccountId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return hash(id, date, debitAccountId, creditAccountId, description, amount);
    }

    @JsonIgnoreProperties({"id"})
    public static final class TransactionBuilder {

        private String id;
        private LocalDate date;
        private String debitAccountId;
        private String creditAccountId;
        private String description;
        private Amount amount;

        @JsonCreator
        private TransactionBuilder() {
            this.date = LocalDate.now();
            this.amount = new Amount(0);
        }

        private TransactionBuilder(Transaction transaction) {
            this.id = transaction.id;
            this.date = transaction.date;
            this.debitAccountId = transaction.debitAccountId;
            this.creditAccountId = transaction.creditAccountId;
            this.description = transaction.description;
            this.amount = transaction.amount;
        }

        @JsonProperty("date")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        public TransactionBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        @JsonProperty("description")
        public TransactionBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        @JsonProperty("creditAccountId")
        public TransactionBuilder withCreditAccountId(String creditId) {
            this.creditAccountId = creditId;
            return this;
        }

        @JsonProperty("debitAccountId")
        public TransactionBuilder withDebitAccountId(String debitId) {
            this.debitAccountId = debitId;
            return this;
        }

        @JsonProperty("amount")
        public TransactionBuilder withAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder copy(Transaction transaction) {
            this.date = transaction.date;
            this.debitAccountId = transaction.debitAccountId;
            this.creditAccountId = transaction.creditAccountId;
            this.description = transaction.description;
            this.amount = transaction.amount;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
