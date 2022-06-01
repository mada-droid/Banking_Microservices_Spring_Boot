package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.entity.BankAccount;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> findAll();

    Optional<Transaction> findById(int theId);

    void save(Transaction transaction);

    void deleteById(int theId);

    List<Transaction> findAllByAccountId(int bankAccountId);



}
