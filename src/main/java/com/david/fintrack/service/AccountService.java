package com.david.fintrack.service;

import com.david.fintrack.model.Account;

import java.util.Arrays;
import java.util.List;

public interface AccountService {
    Account addAccount(Account account);


    Account getAccountByName(String name, long userId);

    int deleteAccount(String name, long userId);

    Account getAccountById(Long accountId);

    List<Account> getAllAccounts(long userId);
}
