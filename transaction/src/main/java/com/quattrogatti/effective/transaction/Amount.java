package com.quattrogatti.effective.transaction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.round;
import static java.lang.String.valueOf;
import static java.util.Objects.hash;

@JsonSerialize(using = AmountSerializer.class)
public final class Amount {

    private final static short precision = 2;
    private final long amount;

    private Amount(long amount) {
        if (abs(amount) > 9_999_999_999d) {
            throw new AmountOutOfRangeException("Number out of range for Amount class.");
        }
        this.amount = amount;
    }

    public Amount(double amount) {
        if (abs(amount) > 9_999_999_999d) {
            throw new AmountOutOfRangeException("Number out of range for Amount class.");
        }
        BigDecimal decimalAmount = new BigDecimal(valueOf(amount));
        this.amount = decimalAmount.movePointRight(precision).longValue();
    }

    public Amount add(Amount adding) {
        return new Amount(this.amount + adding.amount);
    }

    public Amount subtract(Amount substracting) {
        return new Amount(this.amount - substracting.amount);
    }

    public Amount multiply(Amount multiplying) {
        double multiplied = (double) (this.amount * multiplying.amount);
        return new Amount(round(multiplied));
    }

    public Amount divide(Amount dividing) {
        double divided = ((double) this.amount / (double) dividing.amount);
        return new Amount(round(divided));
    }

    public Amount divide(int dividing) {
        double divided = (double) this.amount / (double) dividing;
        return new Amount(round(divided));
    }

    public double toDouble() {
        return new BigDecimal(amount).movePointLeft(precision).doubleValue();
    }

    @Override
    public String toString() {
        return new BigDecimal(amount).movePointLeft(precision).toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount otherAmount = (Amount) o;
        return Objects.equals(amount, otherAmount.amount) &&
                Objects.equals(precision, otherAmount.precision);
    }

    @Override
    public int hashCode() {
        return hash(amount, precision);
    }
}
