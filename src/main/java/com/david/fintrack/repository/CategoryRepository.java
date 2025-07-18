package com.david.fintrack.repository;

import com.david.fintrack.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    int deleteByName(String name);

    Category getCategoryByName(String name);
    // Update category name by its current name
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.name = :newName WHERE c.name = :name")
    int updateCategoryName(String name, String newName);

    Category getCategoryById(Long id);
}
