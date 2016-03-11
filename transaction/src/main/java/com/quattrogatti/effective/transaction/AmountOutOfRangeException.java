package com.quattrogatti.effective.transaction;

public class AmountOutOfRangeException extends RuntimeException {
    public AmountOutOfRangeException(String s) {
        super(s);
    }
}
