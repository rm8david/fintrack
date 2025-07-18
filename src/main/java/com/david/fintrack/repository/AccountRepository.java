package com.david.fintrack.repository;

import com.david.fintrack.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a WHERE a.name = :name")
    Account getAccountByName(String name);

    int deleteByName(String name);
}
