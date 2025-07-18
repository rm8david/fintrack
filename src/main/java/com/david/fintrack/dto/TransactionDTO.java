package com.david.fintrack.dto;

import com.david.fintrack.model.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TransactionDTO {
    private long id;
    private double amount;
    private String description;
    private CategoryType type;
    private LocalDateTime date;

    public TransactionDTO(long id, double amount, String description, CategoryType type, LocalDateTime date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.date = date;
    }
}
