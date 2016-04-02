package com.quattrogatti.effective.transaction.domain;

public class AmountOutOfRangeException extends RuntimeException {
    public AmountOutOfRangeException(String s) {
        super(s);
    }
}
