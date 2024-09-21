package com.BRAINWAVE_JD_01.repository;

import com.BRAINWAVE_JD_01.entity.Account;
import com.BRAINWAVE_JD_01.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
}