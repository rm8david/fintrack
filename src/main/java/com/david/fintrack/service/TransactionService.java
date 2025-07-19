package com.david.fintrack.service;

import com.david.fintrack.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    void deleteTransactionById(Long id);

    List<Transaction> getTransactionsByAccountId(Long accountId);

    int deleteTransactionByIdAndAccountId(Long id, Long accountId);
}
