package com.david.fintrack.dto;

import com.david.fintrack.model.CategoryType;
import com.david.fintrack.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TransactionDTO {
    private long id;
    private double amount;
    private String description;
    private LocalDateTime date;
    private CategoryType type;

    // Constructor que mapea desde Transaction
    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.type = transaction.getType();
    }
}
