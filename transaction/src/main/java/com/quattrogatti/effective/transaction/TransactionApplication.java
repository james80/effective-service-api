package com.quattrogatti.effective.transaction;

import com.quattrogatti.effective.transaction.data.MongoTransactionRepository;
import com.quattrogatti.effective.transaction.data.TransactionRepository;
import com.quattrogatti.effective.transaction.web.TransactionController;

import static com.quattrogatti.effective.common.RegisteredController.open;
import static java.lang.System.getProperty;

public final class TransactionApplication {

    public static void main(String... args) {
        open(getProperty("WEB_PORT"));
        TransactionRepository transactionRepository = new MongoTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        TransactionController transactionController = new TransactionController(transactionService);
        transactionController.register();
    }
}
