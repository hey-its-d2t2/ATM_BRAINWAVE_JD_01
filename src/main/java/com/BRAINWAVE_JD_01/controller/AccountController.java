package com.BRAINWAVE_JD_01.controller;

import com.BRAINWAVE_JD_01.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam Long accountId, @RequestParam double amount){
        accountService.deposit(accountId, amount);
        return ResponseEntity.ok("Deposit successfully");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long accountId, @RequestParam double amount, @RequestParam String pin){
        accountService.withdraw(accountId, amount, pin);
        return ResponseEntity.ok("Withdraw successfully");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount, @RequestParam String pin){
        accountService.transfer(fromAccountId,toAccountId,amount,pin);
        return ResponseEntity.ok("Transfer successful");
    }
    @GetMapping("/balance")
    public ResponseEntity<Double> checkBalance(@RequestParam Long accountId){
        double balance = accountService.checkBalance(accountId);
        return  ResponseEntity.ok(balance);
    }
}
