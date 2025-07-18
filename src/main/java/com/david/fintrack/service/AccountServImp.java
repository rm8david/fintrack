package com.david.fintrack.service;

import com.david.fintrack.model.Account;
import com.david.fintrack.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServImp implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByName(String name) {
        return accountRepository.getAccountByName(name);
    }

    @Override
    @Transactional
    public int deleteAccount(String name) {
        return accountRepository.deleteByName(name);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.getAccountById(accountId);
    }
}
