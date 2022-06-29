package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.BankAccountDTO;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {

    BankAccountDTO findBankAccountById(int accountId);

    void save(BankAccountDTO bankAccountDTO);

    void deleteById(int theId);

    List<BankAccountDTO> findAllByUserId(int userId);

    void update(BankAccountDTO bankAccountDTO);

    List<BankAccountDTO> findAllByRequest();

    BankAccountDTO findBankAccountByAccountNumber(String accountNumber);

    BankAccountDTO findBankAccountByUserId(int userId);

    BankAccountDTO findBankAccountByIban(String iban);


}
