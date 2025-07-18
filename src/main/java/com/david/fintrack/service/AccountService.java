package com.david.fintrack.service;

import com.david.fintrack.model.Account;

public interface AccountService {
    Account addAccount(Account account);

    Account getAccountByName(String name);

    int deleteAccount(String name);
}
