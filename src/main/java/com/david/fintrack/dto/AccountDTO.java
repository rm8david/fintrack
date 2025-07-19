package com.david.fintrack.dto;

import com.david.fintrack.model.Account;
import com.david.fintrack.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AccountDTO {
    private long id;
    private String name;
    private AccountType type;
    private String currency;
    private double balance;
    private Instant createdAt;
    private Instant updatedAt;

    public AccountDTO(long id, String name, AccountType type, String currency, double balance, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Constructor que mapea desde Account
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.type = account.getType();
        this.currency = account.getCurrency();
        this.balance = account.getBalance();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }
}