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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType type;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();
    // Color for UI selection
    private String color;
    // Java
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }

    public Category(CategoryType type, String name, User user) {
        this.type = type;
        this.name = name;
        this.user = user;
    }
}

