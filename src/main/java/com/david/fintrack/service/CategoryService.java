package com.david.fintrack.service;

import com.david.fintrack.model.Category;

public interface CategoryService {
    Category addCategory(Category category);
    int deleteByNameAndUserId(String name, long userId);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name, long userId);
    //update category by name
    int updateCategoryName(String name, String newName, long userId);

}
