package com.example.transactionmicroservice.transactionmicroservice.rest;

import com.example.transactionmicroservice.transactionmicroservice.dao.BankAccountRepository;
import com.example.transactionmicroservice.transactionmicroservice.entity.BankAccount;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import com.example.transactionmicroservice.transactionmicroservice.service.BankAccountService;
import com.example.transactionmicroservice.transactionmicroservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {


    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{bankAccountId}/transactions")
    public List<Transaction> findAllByBankAccountId(@PathVariable int bankAccountId) {
        return transactionService.findAllByAccountId(bankAccountId);
    }

    @PostMapping("/{bankAccountId}/addTransaction")
    public Transaction addTransaction(@PathVariable int bankAccountId) {
        BankAccount bankAccount = bankAccountService.findById(bankAccountId);

        Transaction transaction = new Transaction(OperationType.PRELIEVO,
                13.12, "12/4/63", bankAccount);

        transactionService.save(transaction);

        return transaction;
    }

//    public void setOperazione(String tipologia, double importo){
//
//        if(tipologia=="prelievo"){
//            bankAccount.setBalance(bankAccount.getBalance()-importo);
//        }else if(tipologia=="versamento"){
//            bankAccount.setBalance(bankAccount.getBalance()+importo);
//        }
//    }

}
