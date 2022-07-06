package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import com.madalore.accountmicroserviceML.dao.BankAccountRepository;
import com.madalore.accountmicroserviceML.entity.BankAccount;
import com.madalore.accountmicroserviceML.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;


    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void save(BankAccountDTO bankAccountDTO) {

        BankAccount bankAccount = new BankAccount(bankAccountDTO.getAccountNumber(),
                bankAccountDTO.getIban(), bankAccountDTO.getState(), bankAccountDTO.getBalance(),
                bankAccountDTO.getUserId());
        bankAccount.setId(0);

        bankAccountRepository.save(bankAccount);
    }

    public void update(BankAccountDTO bankAccountDTO) {

        BankAccount bankAccount = new BankAccount(bankAccountDTO.getAccountNumber(),
                bankAccountDTO.getIban(), bankAccountDTO.getState(), bankAccountDTO.getBalance(),
                bankAccountDTO.getUserId());
        bankAccount.setId(bankAccountDTO.getId());

        bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccountDTO> findAllByRequest() {

        List<BankAccount> bankAccountList = bankAccountRepository.findAll();

         List<BankAccountDTO> bankAccountDTOList = bankAccountList.stream().filter(bankAccount ->
                         bankAccount.getState().equals(State.ACTIVATION_REQUEST)
                                 ||bankAccount.getState().equals(State.CLOSURE_REQUEST))
                .map(x -> new BankAccountDTO(x.getIban(),x.getState(),x.getBalance(),
                        x.getUserId(),x.getAccountNumber(),x.getId())).collect(Collectors.toList());

        if (bankAccountDTOList.size() > 0) {
            return bankAccountDTOList;
        } else {
            throw new RuntimeException("Non ci sono richieste al momento !");
        }
    }

    @Override
    public BankAccountDTO findBankAccountByAccountNumber(String accountNumber) {

        BankAccount bankAccount = bankAccountRepository.findBankAccountByAccountNumber(accountNumber);

        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccount.getIban(), bankAccount.getState(),
                bankAccount.getBalance(), bankAccount.getUserId(), bankAccount.getAccountNumber());
        bankAccountDTO.setId(bankAccount.getId());

        return bankAccountDTO;
    }

    @Override
    public BankAccountDTO findBankAccountByUserId(int userId) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountByUserId(userId);
        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccount.getIban(),
                bankAccount.getState(), bankAccount.getBalance(), bankAccount.getUserId(), bankAccount.getAccountNumber());
        bankAccountDTO.setUserId(userId);
        return bankAccountDTO;
    }

    @Override
    public void deleteById(int theId) {
        bankAccountRepository.deleteById(theId);
    }

    public BankAccountDTO findBankAccountById(int accountId) {

        BankAccount bankAccount = bankAccountRepository.findBankAccountById(accountId);

        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccount.getIban(), bankAccount.getState(),
                bankAccount.getBalance(), bankAccount.getUserId(), bankAccount.getAccountNumber());
        bankAccountDTO.setId(bankAccount.getId());

        return bankAccountDTO;
    }

    private List<BankAccountDTO> getAccountDTOSConversion(List<BankAccount> bankAccountList) {

       return bankAccountList.stream().filter(bankAccount ->
                !(bankAccount.getState().equals(State.INACTIVE)))
                .map(x -> new BankAccountDTO(x.getIban(),x.getState(),x.getBalance(),
                        x.getUserId(),x.getAccountNumber(),x.getId())).collect(Collectors.toList());

    }

    public List<BankAccountDTO> findAllByUserId(int userId) {
        List<BankAccount> bankAccountList = bankAccountRepository.findAllByUserId(userId);
        return getAccountDTOSConversion(bankAccountList);
    }

    public BankAccountDTO findBankAccountByIban(String iban) {

       Optional<BankAccount> bankAccountOpt = Optional.ofNullable(bankAccountRepository.findBankAccountByIban(iban));
        BankAccount bankAccount = null;

        if(bankAccountOpt.isPresent()){
            bankAccount = bankAccountOpt.get();
        }else{
            return null;
        }

        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccount.getIban(), bankAccount.getState(),
                bankAccount.getBalance(), bankAccount.getUserId(), bankAccount.getAccountNumber());
        bankAccountDTO.setId(bankAccount.getId());

        return bankAccountDTO;

    }

}
