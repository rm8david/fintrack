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
    public int deleteByNameAndUserId(String name, long userId) {
        return categoryRepository.deleteByNameAndUserId( name, userId);
    }


    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    //TODO implement method to update category

    @Override
    public Category getCategoryByName(String name, long userId) {
        return categoryRepository.getCategoryByName(name, userId);
    }

    @Override
    public int updateCategoryName(String name, String newName, long userId) {
        return categoryRepository.updateCategoryName(name, newName, userId);
    }


}
