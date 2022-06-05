package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import com.madalore.accountmicroserviceML.dao.BankAccountRepository;
import com.madalore.accountmicroserviceML.dto.UserDTO;
import com.madalore.accountmicroserviceML.entity.BankAccount;
import com.madalore.accountmicroserviceML.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccountDTO> findAll() {
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        return getAccountDTOSConversion(bankAccountList);
    }

    @Override
    public List<BankAccountDTO> findAllByUserId(int userId) {
        List<BankAccount> bankAccountList = bankAccountRepository.findAllByUserId(userId);
        return getAccountDTOSConversion(bankAccountList);
    }

    @Override
    public void save(BankAccountDTO bankAccountDTO) {

        User user = new User(bankAccountDTO.getUserDTO().getFirstName()
                , bankAccountDTO.getUserDTO().getLastName(), bankAccountDTO.getUserDTO().getEmail(),
                bankAccountDTO.getUserDTO().getBirthDate(), bankAccountDTO.getUserDTO().getPassword());
        user.setId(0);

        BankAccount bankAccount = new BankAccount(bankAccountDTO.getIban(),
                bankAccountDTO.getState(), bankAccountDTO.getSaldo(),
                user,
                bankAccountDTO.getAccountNumber());
        bankAccount.setId(0);

        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteById(int theId) {
        bankAccountRepository.deleteById(theId);
    }

    @Override
    public List<BankAccountDTO> findAllByUserEmail(String email) {
        List<BankAccount> bankAccountList = bankAccountRepository.findAllByUserEmail(email);
        return getAccountDTOSConversion(bankAccountList);
    }

    @Override
    public List<BankAccountDTO> findAllByState(String state) {
        List<BankAccount> bankAccountList = bankAccountRepository.findAllByState(state);
        return getAccountDTOSConversion(bankAccountList);
    }

    public BankAccountDTO findBankAccountById(int accountId) {

        BankAccount bankAccount = bankAccountRepository.findBankAccountById(accountId);

        User user = bankAccount.getUser();

        UserDTO userDTO = new UserDTO(user.getBirthDate(),
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccount.getIban(), bankAccount.getState(),
                bankAccount.getSaldo(), userDTO, bankAccount.getAccountNumber());

        return bankAccountDTO;
    }

    private List<BankAccountDTO> getAccountDTOSConversion(List<BankAccount> bankAccountList) {
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        BankAccountDTO bankAccountDTO;

        for (BankAccount bankAccount : bankAccountList) {
            UserDTO userDTO = new UserDTO(bankAccount.getUser().getBirthDate(),
                    bankAccount.getUser().getFirstName(), bankAccount.getUser().getLastName(),
                        bankAccount.getUser().getEmail(), bankAccount.getUser().getPassword());

            bankAccountDTO = new BankAccountDTO(bankAccount.getIban(),
                    bankAccount.getState(), bankAccount.getSaldo(), userDTO,
                    bankAccount.getAccountNumber());

            bankAccountDTOList.add(bankAccountDTO);
        }

        return bankAccountDTOList;
    }


}
