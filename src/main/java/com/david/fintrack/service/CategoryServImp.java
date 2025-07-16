package com.david.fintrack.service;

import com.david.fintrack.model.Category;
import com.david.fintrack.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServImp implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        // Set the user ID for the category
        return categoryRepository.save(category);
    }

    @Override
    public int deleteByName(String name) {
        return categoryRepository.deleteByName(name);
    }

    //TODO implement method to update category

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public int updateCategoryName(String name, String newName) {
        return categoryRepository.updateCategoryName(name, newName);
    }


}
