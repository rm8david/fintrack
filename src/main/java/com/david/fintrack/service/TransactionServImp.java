package com.david.fintrack.service;

import com.david.fintrack.model.Transaction;
import com.david.fintrack.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServImp implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }
    //TODO: Implement method to get transaction by amount


    @Override
    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }


}
