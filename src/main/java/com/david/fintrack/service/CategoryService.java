package com.david.fintrack.service;

import com.david.fintrack.model.Category;

public interface CategoryService {
    Category addCategory(Category category);
    int deleteByName(String name);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    //update category by name
    int updateCategoryName(String name, String newName);

}
