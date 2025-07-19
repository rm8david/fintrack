package com.david.fintrack.repository;

import com.david.fintrack.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account getAccountByNameAndUserId(String name, long userId);
    // list all accounts by user id
    List<Account> findAllByUserId(Long userId);

    Account getAccountById(Long id);
    @Query("DELETE FROM Account a WHERE a.name = :name and a.user.id =:userId")
    int deleteByName(@Param("name") String name,@Param("userId") long userId);
}
