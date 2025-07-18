package com.david.fintrack.repository;

import com.david.fintrack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Transaction findById(long id);
    Transaction findFirstByAmountGreaterThan(Double amount);

}
