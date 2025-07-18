package com.david.fintrack.service;

import com.david.fintrack.model.User;
import com.david.fintrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            return user; // Return the user if email and password match
        }
        return null;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Return all users from the repository
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Return null if user not found
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public int deleteByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user!= null && passwordEncoder.matches(password, user.getPassword())) {
            return userRepository.deleteByEmail(email);
        }
        return 0; // Return 0 if user not found or password does not match
    }
    //Transactional is used to ensure that the delete operation is atomic and consistent
    //All the operations within the method will be executed in a single transaction
    @Override
    @Transactional
    public int updateNameByEmail(String name, String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return userRepository.updateNameByEmail(name, email);
        }
        return 0;
    }


}
