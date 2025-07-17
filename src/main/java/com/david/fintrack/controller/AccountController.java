package com.david.fintrack.controller;

import com.david.fintrack.model.Account;
import com.david.fintrack.model.User;
import com.david.fintrack.service.AccountService;
import com.david.fintrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@RequestBody Account account, @RequestParam Long id) {
        User user = userService.getUserById(id);
        account.setUser(user);
        accountService.addAccount(account);
        account.getUser().setPassword(null); // Clear password before returning
        return ResponseEntity.ok(account);
    }
}
