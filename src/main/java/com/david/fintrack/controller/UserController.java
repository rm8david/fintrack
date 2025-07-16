package com.david.fintrack.controller;

import com.david.fintrack.model.User;
import com.david.fintrack.service.UserService;
import com.david.fintrack.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.getUserByEmailAndPassword(email, password);
            if (user != null) {
                user.setPassword(null); // Clear password before returning
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Error: User not found or incorrect credentials"));
            }
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        }catch (DataIntegrityViolationException ex){
            if(ex.getMessage() != null && ex.getMessage().contains("users_unique")){
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(new ErrorResponse("Error: The email is already in use"));
            }
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String email, @RequestParam String password) {
        try {
            if (userService.deleteByEmailAndPassword(email, password)>0) {
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Error: User not found or incorrect credentials"));
            }
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }

    @PutMapping("/updateName")
    public ResponseEntity<?> updateName(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        try {
            if (userService.updateNameByEmail(name, email, password) > 0) {
                return ResponseEntity.ok("User name updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Error: User not found or incorrect credentials"));
            }
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
        }
    }
}