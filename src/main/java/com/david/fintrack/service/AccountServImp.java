package com.david.fintrack.service;

import com.david.fintrack.model.Account;
import com.david.fintrack.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServImp implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByName(String name, long userId) {
        return accountRepository.getAccountByNameAndUserId( name, userId);
    }

    @Override
    @Transactional
    public int deleteAccount(String name, long userId) {
        return accountRepository.deleteByName(name, userId);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.getAccountById(accountId);
    }

    @Override
    public List<Account> getAllAccounts( long userId) {
        return accountRepository.findAllByUserId(userId);
    }
}
