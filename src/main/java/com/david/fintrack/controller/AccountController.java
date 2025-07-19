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
    public ResponseEntity<?> addAccount(@RequestBody Account account, @RequestParam Long userId) {
        User user = userService.getUserById(userId);
        account.setUser(user);
        accountService.addAccount(account);
        account.getUser().setPassword(null); // Clear password before returning
        return ResponseEntity.ok(account);
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getAccountByName(@RequestParam String name,@RequestParam long userId) {
        Account account = accountService.getAccountByName( name, userId);
        if (account == null) {
            return ResponseEntity.status(404).body("Error: Account not found");
        }
        account.getUser().setPassword(null);
        return ResponseEntity.ok(account);
    }
    // get all accounts by user id
    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDTO>> getAllAccounts( @RequestParam long userId) {
        List<Account> accounts = accountService.getAllAccounts(userId);
        List<AccountDTO> dtos = accounts.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
        return  ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam String name, @RequestParam long userId) {
        int deletedCount = accountService.deleteAccount(name, userId);
        if (deletedCount == 0) {
            return ResponseEntity.status(404).body("Error: Account not found");
        }
        return ResponseEntity.ok("Account deleted successfully");
    }
}
