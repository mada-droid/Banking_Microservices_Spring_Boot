package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    void save(TransactionDTO transaction);

    boolean sendAmountOp(AmountMessageDTO amountMessageDTO);

    List<TransactionDTO> findAllByQueryMadePrelievoLastTenLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadePrelievoLastTwentyLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadePrelievoLastFiftyLimit(int accountId);

    List<TransactionDTO> findAllByQueryMadeDepositoLastTenLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadeDepositoLastTwentyLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadeDepositoLastFiftyLimit(int accountId);

    List<TransactionDTO> findAllByQueryMadeBonificoLastTenLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadeBonificoLastTwentyLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadeBonificoLastFiftyLimit(int accountId);

    List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTenLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTwentyLimit(int accountId);
    List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastFiftyLimit(int accountId);

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

    List<TransactionDTO> findAllByAccountIdOrderLimitTen(int accountId);
    List<TransactionDTO> findAllByAccountIdOrderLimitTwenty(int accountId);
    List<TransactionDTO> findAllByAccountIdOrderLimitFifty(int accountId);

}
