package com.david.fintrack.repository;

import com.david.fintrack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Transaction findById(long id);
    List<Transaction> findByAccountId(Long accountId);
    @Transactional
    int deleteByIdAndAccountId(Long id, Long accountId);
}
