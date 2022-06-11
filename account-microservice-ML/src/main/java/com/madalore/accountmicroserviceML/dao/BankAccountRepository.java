package com.madalore.accountmicroserviceML.dao;

import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import com.madalore.accountmicroserviceML.entity.BankAccount;
import com.madalore.accountmicroserviceML.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    BankAccount findBankAccountById(int bankAccountId);

    List<BankAccount> findAllByUserId(int userId);

    BankAccount findBankAccountByAccountNumber(String accountNumber);

    BankAccount findBankAccountByIban(String iban);


}


