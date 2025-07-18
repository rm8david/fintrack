package com.david.fintrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Transaction(double amount, String description, CategoryType type) {
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public Transaction(double amount, String description, CategoryType type, Account account) {
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.account = account;
    }
}
