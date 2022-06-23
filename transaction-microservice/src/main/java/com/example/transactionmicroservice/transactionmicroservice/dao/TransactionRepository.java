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



}
