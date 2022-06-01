package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.entity.BankAccount;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;

import java.util.List;

public interface BankAccountService {

    List<BankAccount> findAll();

    BankAccount findById(int theId);

    void save(BankAccount bankAccount);

    void deleteById(int theId);
}
