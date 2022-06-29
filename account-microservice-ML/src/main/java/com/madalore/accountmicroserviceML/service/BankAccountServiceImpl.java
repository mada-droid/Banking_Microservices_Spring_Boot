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

        List<BankAccountDTO> bankAccountDTOSList = new ArrayList<>();
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();

        for (BankAccount bankAccount : bankAccountList) {
            if ((bankAccount.getState().equals(State.CLOSURE_REQUEST))
                    || bankAccount.getState().equals(State.ACTIVATION_REQUEST)) {
                BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccount.getIban(), bankAccount.getState(),
                        bankAccount.getBalance(), bankAccount.getUserId(), bankAccount.getAccountNumber());
                bankAccountDTO.setId(bankAccount.getId());
                bankAccountDTOSList.add(bankAccountDTO);
            }
        }
        if (bankAccountDTOSList.size() > 0) {
            return bankAccountDTOSList;
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
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        BankAccountDTO bankAccountDTO;

        for (BankAccount bankAccount : bankAccountList) {
            if (!(bankAccount.getState().toString().equals("INACTIVE"))) {
                bankAccountDTO = new BankAccountDTO(bankAccount.getIban(),
                        bankAccount.getState(), bankAccount.getBalance(), bankAccount.getUserId(),
                        bankAccount.getAccountNumber());
                bankAccountDTO.setId(bankAccount.getId());

                bankAccountDTOList.add(bankAccountDTO);
            }
        }

        return bankAccountDTOList;
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
