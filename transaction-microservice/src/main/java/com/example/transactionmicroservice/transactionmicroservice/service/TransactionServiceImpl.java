package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.dao.TransactionRepository;
import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

//    @Autowired
//    private CustomMessageSender customMessageSender;

    @Autowired
    private AmountMessageSender amountMessageSender;

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
    public void save(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(transactionDTO.getOperationType(), transactionDTO.getAmount(),
                transactionDTO.getData(), transactionDTO.getAccountId(), transactionDTO.getCausal());
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(int theId) {
        transactionRepository.deleteById(theId);
    }


    @Override
    public List<TransactionDTO> findAllByAccountId(int bankAccountId) {
        return transactionRepository.findAllByAccountIdOrderById(bankAccountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByOperationType(OperationType operationType) {
        return transactionRepository.findAllByOperationType(operationType)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal())).collect(Collectors.toList());
    }

//    // verifica disponibilità saldo per operazioni di addebito sul c.c.
//    @Override
//    public boolean checkAvailabilityBalance(int accountId, double amount) {
//        /* Richiamare il microservizio 'account-microservice' per farsi restituire il bilancio del c.c. */
//            Double saldo = customMessageSender.sendMessage(accountId);
//        return amount > saldo.doubleValue();
//    }

    @Override
    public boolean sendAmountOp(AmountMessageDTO amountMessageDTO) {
        return amountMessageSender.sendAmountMessage(amountMessageDTO);
    }

    @Override
    public List<TransactionDTO> findAllByIdIsNotNullOrderByIdDesc() {

        List<Transaction> transactionList = transactionRepository.findAllByIdIsNotNullOrderByIdDesc();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transactionDTOList.size() == 30) {
                break;
            }
            TransactionDTO transactionDTO = new TransactionDTO(transaction.getOperationType(),
                    transaction.getAmount(), transaction.getDate(), transaction.getAccountId(),
                    transaction.getCausal());
            transactionDTO.setId(transaction.getId());
            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc(int accountId) {
        List<Transaction> transactionList = transactionRepository
                .findAllByAccountIdOrderByIdDesc(accountId);
        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transactionDTOList.size() == 10) {
                break;
            }
            TransactionDTO transactionDTO = new TransactionDTO(transaction.getOperationType(),
                    transaction.getAmount(), transaction.getDate(), transaction.getAccountId(),
                    transaction.getCausal());
            transactionDTO.setId(transaction.getId());
            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;

    }

}






