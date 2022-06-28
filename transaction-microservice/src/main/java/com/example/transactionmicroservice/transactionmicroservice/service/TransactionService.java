package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    void save(TransactionDTO transaction);

    boolean sendAmountOp(AmountMessageDTO amountMessageDTO);

    List<TransactionDTO> findAllByIdIsNotNullOrderByIdDesc();

    List<TransactionDTO> findAllByAccountId(int bankAccountId);

    List<TransactionDTO> findAllByQueryMadePrelievoLastTen(int accountId);
    List<TransactionDTO> findAllByQueryMadePrelievoLastTwenty(int accountId);
    List<TransactionDTO> findAllByQueryMadePrelievoLastFifty(int accountId);

    List<TransactionDTO> findAllByQueryMadeDepositoLastTen(int accountId);
    List<TransactionDTO> findAllByQueryMadeDepositoLastTwenty(int accountId);
    List<TransactionDTO> findAllByQueryMadeDepositoLastFifty(int accountId);

    List<TransactionDTO> findAllByQueryMadeBonificoLastTen(int accountId);
    List<TransactionDTO> findAllByQueryMadeBonificoLastTwenty(int accountId);
    List<TransactionDTO> findAllByQueryMadeBonificoLastFifty(int accountId);

    List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTen(int accountId);
    List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTwenty(int accountId);
    List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastFifty(int accountId);

    List<TransactionDTO> findAllByAccountIdOrderByIdDesc(int accountId);
    List<TransactionDTO> findAllByAccountIdOrderByIdDesc20(int accountId);
    List<TransactionDTO> findAllByAccountIdOrderByIdDesc50(int accountId);

}
