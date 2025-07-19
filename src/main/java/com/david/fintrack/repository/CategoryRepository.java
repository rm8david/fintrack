package com.david.fintrack.repository;

import com.david.fintrack.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    int deleteByNameAndUserId(String name, long userId);
    @Query("SELECT c FROM Category c WHERE c.name = :name and c.user.id = :userId")
    Category getCategoryByName(@Param("name") String name,@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.name = :newName WHERE c.name = :name and c.user.id = :userId")
    int updateCategoryName( @Param("name") String name, @Param("newName") String newName, @Param("userId") long userId);

    Category getCategoryById(Long id);
}
