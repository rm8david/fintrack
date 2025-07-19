package com.david.fintrack.controller;

import com.david.fintrack.dto.TransactionDTO;
import com.david.fintrack.model.Account;
import com.david.fintrack.model.CategoryType;
import com.david.fintrack.model.Transaction;
import com.david.fintrack.service.AccountService;
import com.david.fintrack.service.CategoryService;
import com.david.fintrack.service.TransactionService;
import com.david.fintrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction, @RequestParam Long accountId) {

        Account account = accountService.getAccountById(accountId);

        // Actualiza el balance según el tipo de transacción
        double amount = transaction.getAmount();
        if (transaction.getType() == CategoryType.EXPENSE) {
            account.setBalance(account.getBalance() - amount);
        } else {
            account.setBalance(account.getBalance() + amount);
        }
        accountService.addAccount(account); // Guarda la cuenta actualizada

        transaction.setAccount(account);
        transaction.setDate(LocalDateTime.now());
        transactionService.addTransaction(transaction);
        transaction.getAccount().getUser().setPassword(null); // Clear password before returning
        return transaction;
    }

    @GetMapping("/byAccount")
    public List<TransactionDTO> getTransactionsByAccount (@RequestParam Long accountId){
        return transactionService.getTransactionsByAccountId(accountId)
                .stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteByNameAndAccountId(@RequestParam Long id, @RequestParam Long accountId) {
        int deletedCount = transactionService.deleteTransactionByIdAndAccountId(id, accountId);
        if (deletedCount > 0) {
            return ResponseEntity.ok("Transaction deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Transaction not found");
        }
    }


}
