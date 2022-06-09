package com.example.transactionmicroservice.transactionmicroservice.dao;

import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findAllByAccountIdOrderById(int bankAccountId);
    List<Transaction> findAllByOperationType(OperationType operationType);
    List<Transaction> findAllByAmount(double amount);
    List<Transaction> findAllByDate(Date date);

}
