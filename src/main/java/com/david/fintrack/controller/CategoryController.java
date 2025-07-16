// Java
package com.david.fintrack.controller;

import com.david.fintrack.model.Category;
import com.david.fintrack.model.User;
import com.david.fintrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.david.fintrack.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name) {
        Category category = categoryService.getCategoryByName(name);
        if (category == null) {
            return ResponseEntity.status(404).body("Error: Category not found");
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category, @RequestParam Long user_id) {
        try {
            User user = userService.getUserById(user_id);
            if (user == null) {
                return ResponseEntity.status(404).body("Usuario no encontrado");
            }
            category.setUser(user);
            Category savedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(savedCategory);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestParam String name, @RequestParam String newName) {
        try {
            int updatedCategory = categoryService.updateCategoryName(name, newName);
            if (updatedCategory == 0) {
                return ResponseEntity.status(404).body("Error: Category not found");
            }
            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam String name) {
        try {
            int deletedCount = categoryService.deleteByName(name);
            if (deletedCount == 0) {
                return ResponseEntity.status(404).body("Error: Category not found");
            }
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }
}