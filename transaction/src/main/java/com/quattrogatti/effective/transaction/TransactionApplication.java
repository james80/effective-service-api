package com.quattrogatti.effective.transaction;

import static com.quattrogatti.effective.common.RegisteredController.open;
import static java.lang.System.getProperty;

public class TransactionApplication {

    public static void main(String... args) {
        open(getProperty("WEB_PORT"));
        TransactionRepository transactionRepository = new MongoTransactionRepository();
        TransactionService transactionService = new DefaultTransactionService(transactionRepository);
        TransactionController transactionController = new TransactionController(transactionService);
        transactionController.register();
    }
}
