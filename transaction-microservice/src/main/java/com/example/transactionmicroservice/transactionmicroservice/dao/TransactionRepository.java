package com.example.transactionmicroservice.transactionmicroservice.dao;

import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findAllByAccountIdOrderById(int bankAccountId);
    List<Transaction> findAllByIdIsNotNullOrderByIdDesc();
    List<Transaction> findAllByAccountIdOrderByIdDesc(int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE account_id = :account_id order by -id limit 10",
            nativeQuery = true)
    List<Transaction> findAllByAccountIdOrderLimitTen(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE account_id = :account_id order by -id limit 20",
            nativeQuery = true)
    List<Transaction> findAllByAccountIdOrderLimitTwenty(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE account_id = :account_id order by -id limit 50",
            nativeQuery = true)
    List<Transaction> findAllByAccountIdOrderLimitFifty(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'PRELIEVO' order by -id",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadePrelievo();

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'DEPOSITO' order by -id",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeDeposito();

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'BONIFICO' order by -id",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeBonifico();

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'RICARICA_TELEFONICA' order by -id",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeRicaricaTelefonica();

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'PRELIEVO' and account_id = :account_id order by -id limit 10",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadePrelievoLastTenLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'PRELIEVO' and account_id = :account_id order by -id limit 20",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadePrelievoLastTwentyLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'PRELIEVO' and account_id = :account_id order by -id limit 50",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadePrelievoLastFiftyLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'DEPOSITO' and account_id = :account_id order by -id limit 10",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeDepositoLastTenLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'DEPOSITO' and account_id = :account_id order by -id limit 20",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeDepositoLastTwentyLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'DEPOSITO' and account_id = :account_id order by -id limit 50",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeDepositoLastFiftyLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'BONIFICO' and account_id = :account_id order by -id limit 10",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeBonificoLastTenLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'BONIFICO' and account_id = :account_id order by -id limit 20",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeBonificoLastTwentyLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'BONIFICO' and account_id = :account_id order by -id limit 50",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeBonificoLastFiftyLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'RICARICA_TELEFONICA' and account_id = :account_id order by -id limit 10",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeRicaricaTelefonicaLastTenLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'RICARICA_TELEFONICA' and account_id = :account_id order by -id limit 20",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeRicaricaTelefonicaLastTwentyLimit(@Param("account_id") int accountId);

    @Query(
            value = "SELECT * FROM `transaction-microservice`.transaction WHERE operation_type = 'RICARICA_TELEFONICA' and account_id = :account_id order by -id limit 50",
            nativeQuery = true)
    List<Transaction> findAllByQueryMadeRicaricaTelefonicaLastFiftyLimit(@Param("account_id") int accountId);




}
