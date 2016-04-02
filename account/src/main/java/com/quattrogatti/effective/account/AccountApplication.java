package com.quattrogatti.effective.account;

import static com.quattrogatti.effective.common.RegisteredController.open;
import static java.lang.System.getProperty;

public final class AccountApplication {

    public static void main(String[] args) {
        open(getProperty("WEB_PORT"));
    }
}
