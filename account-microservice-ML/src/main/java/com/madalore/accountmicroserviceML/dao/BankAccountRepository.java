package com.madalore.accountmicroserviceML.dao;

import com.madalore.accountmicroserviceML.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

//    List<BankAccount> findAllByUserFirstNameAndStato(String firstName, String stato);

    List<BankAccount> findAllByUserId(int userId);

    BankAccount findBankAccountById(int bankAccountId);

    List<BankAccount> findAllByUserEmail(String email);

    List<BankAccount> findAllByState(String stato);


}


