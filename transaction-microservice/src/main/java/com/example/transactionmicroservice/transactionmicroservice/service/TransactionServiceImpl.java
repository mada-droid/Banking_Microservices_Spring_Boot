package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.dao.TransactionRepository;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(int theId) {

        Optional<Transaction> result = transactionRepository.findById(theId);

        Transaction transaction = null;

        if (result.isPresent()) {
            transaction = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Transaction with id: " + theId + " can't be found");
        }

        return Optional.of(transaction);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(int theId) {
        transactionRepository.deleteById(theId);
    }

    @Override
    public List<Transaction> findAllByAccountId(int bankAccountId) {
        return transactionRepository.findAllByBankAccount_IdOrderById(bankAccountId);
    }

}
