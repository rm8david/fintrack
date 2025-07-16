package com.david.fintrack.service;

import com.david.fintrack.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User addUser(User user);
    int deleteByEmailAndPassword(String email, String password);

    // Update name by email
    int updateNameByEmail(String name, String email,String password);

    //get user by id
    User getUserById(Long id);
    // Get user by email and password
    User getUserByEmailAndPassword(String email, String password);
}
