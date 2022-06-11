package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> findAll();

    Optional<Transaction> findById(int theId);

    void save(TransactionDTO transaction);

    void deleteById(int theId);

    List<TransactionDTO> findAllByAccountId(int bankAccountId);

    List<TransactionDTO> findAllByOperationType(OperationType operationType);

    boolean sendAmountOp(AmountMessageDTO amountMessageDTO);

    List<TransactionDTO> findAllByIdIsNotNullOrderByIdDesc();

    List<TransactionDTO> findAllByAccountIdOrderByIdDesc(int accountId);



}
