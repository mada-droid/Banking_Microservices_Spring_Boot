package com.example.transactionmicroservice.transactionmicroservice.dao;

import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findAllByBankAccount_IdOrderById(int bankAccountId);
}
