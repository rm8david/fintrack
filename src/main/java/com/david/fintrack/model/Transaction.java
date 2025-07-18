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
    private CategoryType type;
    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Transaction(double amount, String description, Account account, Category category, User user) {
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.category = category;
        this.user = user;
    }
}
