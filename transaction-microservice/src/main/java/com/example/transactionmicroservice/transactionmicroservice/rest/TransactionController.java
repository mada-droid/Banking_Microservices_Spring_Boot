package com.example.transactionmicroservice.transactionmicroservice.rest;

import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransferMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransferResponseDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.service.AmountMessageSender;
import com.example.transactionmicroservice.transactionmicroservice.service.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Messager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@SecurityRequirement(name = "javainuseapi")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    private AmountMessageSender amountMessageSender;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //All transactions for a specific bank account,
    //taking in a pathVariable of the bankAccountId
    @GetMapping("/{bankAccountId}/transactions")
    public List<TransactionDTO> findAllByBankAccountId(@PathVariable int bankAccountId) {
        return transactionService.findAllByAccountId(bankAccountId);
    }

    //All transactions having the same op type,
    //taking in a pathVariable of the operation type
    @GetMapping("/{operationType}/transactions")
    public List<TransactionDTO> findAllByOpType(@PathVariable OperationType operationType) {
        return transactionService.findAllByOperationType(operationType);
    }

    //making a deposit(passing in a positive integer) or a withdrawal(passing in a negative integer)
    //having a body of a new DTO containing
    // amount(double), accountId(int), causal(String)
    //eventually calls rabbitMQ creating a queue, calling account MS to update its balance
    @PostMapping("/transaction")
    public ResponseEntity<?> addTransaction(@RequestBody AmountMessageDTO amountMessageDTO) {

        if (amountMessageDTO.getAmount() == 0) {
            return ResponseEntity.badRequest().body("L'importo non può essere pari a 0");
        }
        boolean response = transactionService.sendAmountOp(amountMessageDTO);
        if (response) {
                OperationType type;
                if (amountMessageDTO.getAmount() > 0) {
                    type = OperationType.DEPOSITO;
                } else {
                    type = OperationType.PRELIEVO;
                }
                TransactionDTO transactionDTO = new TransactionDTO(type, amountMessageDTO.getAmount(),
                        new Date(), amountMessageDTO.getAccountId(),amountMessageDTO.getCausal());
                transactionService.save(transactionDTO);
                return ResponseEntity.ok("Transizione effettuata con successo !");
        }
        else {
            return ResponseEntity.badRequest().body("Saldo disponibile insufficiente per effettuare l'operazione!!");
        }
    }


    //Making a transfer(bonifico) from one existing account in the db to another existing account
    //passing in a body of a new DTO containing
    // originIban(String), destinationIban(String), amount(double), causal(String)
    //eventually calls rabbitMQ creating a queue, calling account MS to update its balance
    @PostMapping("/transfer")
    public ResponseEntity<?> addTransfer(@RequestBody TransferMessageDTO transferMessageDTO) {

        if (transferMessageDTO.getAmount() == 0) {
            return ResponseEntity.badRequest().body("L'importo non può essere pari 0");
        }
        HashMap<String,Integer> response = (HashMap<String, Integer>)
                amountMessageSender.sendTransferMessage(transferMessageDTO);
//        Object responseDTO = amountMessageSender.sendTransferMessage(transferMessageDTO);

        if (response != null) {

            TransactionDTO transactionDTOFrom = new TransactionDTO(OperationType.BONIFICO,
                    -transferMessageDTO.getAmount(), new Date(), response.get("accountIdFrom") ,
                    transferMessageDTO.getCausal());

            TransactionDTO transactionDTOTo = new TransactionDTO(OperationType.BONIFICO,
                    transferMessageDTO.getAmount(), new Date(), response.get("accountIdTo"),
                    transferMessageDTO.getCausal());

            transactionService.save(transactionDTOFrom);
            transactionService.save(transactionDTOTo);

            return ResponseEntity.ok("bonifico effettuata con successo !");
        }
        else {
            return ResponseEntity.badRequest().body("Errore!");
        }
    }

    //Recharging the user's phone credit(top up)
    //having a body of a new DTO containing(amountMessageDTO)
    // amount(double), accountId(int), causal(String)
    //eventually calls rabbitMQ creating a queue, calling account MS to update its balance
    @PostMapping("/ricarica_telefonica")
    public ResponseEntity<?> addTransactionRicaricaTelefonica(@RequestBody AmountMessageDTO amountMessageDTO) {

        if (amountMessageDTO.getAmount() == 0) {
            return ResponseEntity.badRequest().body("L'importo non può essere pari a 0");
        }
        int amount =(int)amountMessageDTO.getAmount();
        if(amount > 0){
            amountMessageDTO.setAmount(-amountMessageDTO.getAmount());
        }
        boolean response = transactionService.sendAmountOp(amountMessageDTO);

        if (response) {
            OperationType  type = OperationType.RICARICA_TELEFONICA;
            TransactionDTO transactionDTO = new TransactionDTO(type, amount,
                    new Date(), amountMessageDTO.getAccountId(),amountMessageDTO.getCausal());
            transactionService.save(transactionDTO);
            return ResponseEntity.ok("Ricarica telefonica effettuata con successo !");
        }
        else {
            return ResponseEntity.badRequest().body("Saldo disponibile insufficiente per effettuare l'operazione!!");
        }
    }



    //due to specific requirements
    //prints out last 30 transactions of all bank accounts
    //visible only to Employees
    @GetMapping("/all_operations")
    public List<TransactionDTO> getOperations(){
        return transactionService.findAllByIdIsNotNullOrderByIdDesc();
    }

    //due to specific requirements
    //prints out last 10 transactions of a certain bank account
    //takes in a parameter(pathVariable) accountId(int)
    @GetMapping("/last_ten_operation/{accountId}")
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc(@PathVariable int accountId){
        return transactionService.findAllByAccountIdOrderByIdDesc(accountId);
    }

    //due to specific requirements
    //prints out last 20 transactions of a certain bank account
    //takes in a parameter(pathVariable) accountId(int)
    @GetMapping("/last_twenty_operation/{accountId}")
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc20(@PathVariable int accountId){
        return transactionService.findAllByAccountIdOrderByIdDesc20(accountId);
    }

    //due to specific requirements
    //prints out last 50 transactions of a certain bank account
    //takes in a parameter(pathVariable) accountId(int)
    @GetMapping("/last_fifty_operation/{accountId}")
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc50(@PathVariable int accountId){
        return transactionService.findAllByAccountIdOrderByIdDesc50(accountId);
    }
}





