package com.example.transactionmicroservice.transactionmicroservice.dao;

import com.example.transactionmicroservice.transactionmicroservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
