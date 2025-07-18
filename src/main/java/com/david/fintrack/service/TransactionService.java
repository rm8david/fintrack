package com.david.fintrack.service;

import com.david.fintrack.model.Transaction;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    Transaction getTransactionByAmount(Double amount);
    void deleteTransactionById(Long id);
}
