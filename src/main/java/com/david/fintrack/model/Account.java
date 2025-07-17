package com.david.fintrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private double balance;
    private String currency;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Account(String name, AccountType type, String currency, User user) {
        this.name = name;
        this.type = type;
        this.currency = currency;
        this.user = user;
    }
}
