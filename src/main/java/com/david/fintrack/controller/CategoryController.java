// Java
package com.david.fintrack.controller;

import com.david.fintrack.model.Category;
import com.david.fintrack.model.User;
import com.david.fintrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.david.fintrack.service.CategoryService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name, @RequestParam long userId) {
        Category category = categoryService.getCategoryByName(name, userId);
        if (category == null) {
            return ResponseEntity.status(404).body("Error: Category not found");
        }
        category.getUser().setPassword(null); // Clear password before returning
        return ResponseEntity.ok(category);
    }
    /* Endpoint to add a new category with a related user ID and setting the user
    password to null
    before returning the response */
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category, @RequestParam Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.status(404).body("Usuario no encontrado");
            }

            category.setUser(user);
            Category savedCategory = categoryService.addCategory(category);
            category.getUser().setPassword(null); // Clear password before returning
            return ResponseEntity.ok(savedCategory);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }
    // Endpoint to update a category name, return a response indicating success or failure
    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestParam String name, @RequestParam String newName, @RequestParam long userId) {
        try {
            int updatedCategory = categoryService.updateCategoryName(name, newName, userId);
            if (updatedCategory == 0) {
                return ResponseEntity.status(404).body("Error: Category not found");
            }

            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }
// Endpoint to delete a category by name, return a response indicating success or failure
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam String name, @RequestParam long userId) {
        try {
            int deletedCount = categoryService.deleteByNameAndUserId( name, userId);
            if (deletedCount == 0) {
                return ResponseEntity.status(404).body("Error: Category not found");
            }
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }
}