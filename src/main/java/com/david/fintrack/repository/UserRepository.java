package com.david.fintrack.repository;

import com.david.fintrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    /** CRUD operations for User entity */
    // GET operation to find a user by email
    User findByEmail(String email);
    //PUT operation to save a user
    User save(User user);
    // POST METHOD: Custom query to update the user's name by email
    @Query("update User u set u.name = :name where u.email = :email")
    @Modifying// This annotation is used to indicate that the query is an update operation, not a select query
    int updateNameByEmail(String name, String email);
    // DELETE operation to delete a user by email
    @Transactional
    int deleteByEmail(String email);

    User getUserByEmailAndPassword (String email, String password);
}
