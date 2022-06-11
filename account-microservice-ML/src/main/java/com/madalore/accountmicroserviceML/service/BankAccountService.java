package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import com.madalore.accountmicroserviceML.entity.BankAccount;
import com.madalore.accountmicroserviceML.entity.State;

import java.util.List;

public interface BankAccountService {

    BankAccountDTO findBankAccountById(int accountId);

    void save(BankAccountDTO bankAccountDTO);

    void deleteById(int theId);

    List<BankAccountDTO> findAllByUserId(int userId);

    void update(BankAccountDTO bankAccountDTO);

    List<BankAccountDTO> findAllByRequest();

    BankAccountDTO findBankAccountByAccountNumber(String accountNumber);

    BankAccountDTO findBankAccountByIban(String iban);


}
