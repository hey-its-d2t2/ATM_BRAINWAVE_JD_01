package com.BRAINWAVE_JD_01.service;

import com.BRAINWAVE_JD_01.entity.Account;
import com.BRAINWAVE_JD_01.entity.User;

public interface AccountService {
    Account createAccount(User user);
    void deposit(Long accountId, double account);
    void withdraw(Long accountId, double account, String pin);
    void transfer(Long fromAccountId, Long toAccountId, double amount,String pin);
    double checkBalance(Long accountId);

}
