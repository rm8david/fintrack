package com.david.fintrack.controller;

import com.david.fintrack.dto.AccountDTO;
import com.david.fintrack.model.Account;
import com.david.fintrack.model.User;
import com.david.fintrack.service.AccountService;
import com.david.fintrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/get")
    public ResponseEntity<?> getAccountByName(@RequestParam String name) {
        Account account = accountService.getAccountByName(name);
        if (account == null) {
            return ResponseEntity.status(404).body("Error: Account not found");
        }
        account.getUser().setPassword(null);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts()
                .stream()
                .map(account -> new AccountDTO(
                        account.getId(),
                        account.getName(),
                        account.getType(),
                        account.getCurrency()))
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam String name) {
        int deletedCount = accountService.deleteAccount(name);
        if (deletedCount == 0) {
            return ResponseEntity.status(404).body("Error: Account not found");
        }
        return ResponseEntity.ok("Account deleted successfully");
    }
}
