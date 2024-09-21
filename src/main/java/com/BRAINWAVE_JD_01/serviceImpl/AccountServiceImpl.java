package com.BRAINWAVE_JD_01.serviceImpl;

import com.BRAINWAVE_JD_01.entity.Account;
import com.BRAINWAVE_JD_01.entity.Transaction;
import com.BRAINWAVE_JD_01.entity.User;
import com.BRAINWAVE_JD_01.repository.AccountRepository;
import com.BRAINWAVE_JD_01.repository.TransactionRepository;
import com.BRAINWAVE_JD_01.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

   @Autowired
   private AccountRepository accountRepository;

   @Autowired
   private TransactionRepository transactionRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;


    @Override
    public Account createAccount(User user) {
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setPin(passwordEncoder.encode(generateRandomPin()));
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    public void deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()->new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        //Log Transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("Deposit");
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDetails("Deposited to account");
        transactionRepository.save(transaction);

    }

    @Override
    public void withdraw(Long accountId, double amount, String pin) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Account not found"));

        if (passwordEncoder.matches(pin, account.getPin()) && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);

            //Log Transaction
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setType("Withdraw");
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setDetails("Withdraw from account");
            transactionRepository.save(transaction);
        }else {
            throw  new RuntimeException("Invalid PIN or insufficient balance");
        }
    }

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, double amount, String pin) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                                .orElseThrow(()-> new RuntimeException("From account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                            .orElseThrow(()-> new RuntimeException("To account not found"));

        if(passwordEncoder.matches(pin,fromAccount.getPin()) && fromAccount.getBalance()>=amount){
            fromAccount.setBalance(fromAccount.getBalance()-amount);
            toAccount.setBalance(toAccount.getBalance()+amount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            //Log transactions for both accounts

            Transaction toTransaction = new Transaction();
            toTransaction.setAccount(toAccount);
            toTransaction.setAmount(amount);
            toTransaction.setType("Transfer");
            toTransaction.setTimestamp(LocalDateTime.now());
            toTransaction.setDetails("Received from account " +fromAccount.getAccountNumber());
            transactionRepository.save(toTransaction);
        }else {
            throw new RuntimeException("Invalid PIN or Insufficient balance");
        }
    }

    @Override
    public double checkBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                            .orElseThrow(()-> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    private String generateRandomPin(){
        return String.valueOf(new Random().nextInt(9000)+1000); //returns 4 digit random pin
    }
    private String generateAccountNumber(){
        return UUID.randomUUID().toString();
    }
}
