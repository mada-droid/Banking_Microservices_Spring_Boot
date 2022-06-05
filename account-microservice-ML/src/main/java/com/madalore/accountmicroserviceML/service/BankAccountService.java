package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.BankAccountDTO;

import java.util.List;

public interface BankAccountService {

    List<BankAccountDTO> findAll();

    List<BankAccountDTO> findAllByUserId(int userId);

    BankAccountDTO findBankAccountById(int accountId);

    void save(BankAccountDTO bankAccountDTO);

    void deleteById(int theId);

    List<BankAccountDTO> findAllByUserEmail(String email);

    List<BankAccountDTO> findAllByState(String state);


}
