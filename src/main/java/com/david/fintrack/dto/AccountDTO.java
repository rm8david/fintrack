package com.david.fintrack.dto;

import com.david.fintrack.model.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private long id;
    private String name;
    private AccountType type;
    private String currency;

    public AccountDTO(long id, String name, AccountType type, String currency) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.currency = currency;
    }
}
