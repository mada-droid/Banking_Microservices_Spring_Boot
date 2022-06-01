package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.dao.BankAccountRepository;
import com.example.transactionmicroservice.transactionmicroservice.entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount findById(int theId) {
        Optional<BankAccount> result = bankAccountRepository.findById(theId);

        BankAccount bankAccount = null;

        if (result.isPresent()) {
            bankAccount = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("bankAccount with id: " + theId + " can't be found");
        }

        return bankAccount;
    }

    @Override
    public void save(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteById(int theId) {
        bankAccountRepository.deleteById(theId);
    }
}
