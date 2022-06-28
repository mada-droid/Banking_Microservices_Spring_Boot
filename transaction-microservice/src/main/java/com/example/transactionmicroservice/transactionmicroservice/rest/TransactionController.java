package com.example.transactionmicroservice.transactionmicroservice.rest;

import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransferMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.service.AmountMessageSender;
import com.example.transactionmicroservice.transactionmicroservice.service.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    //Prints all transactions for a specific bank account,
    //taking in a pathVariable of the bankAccountId
    @GetMapping("/{bankAccountId}/transactions")
    public List<TransactionDTO> findAllByBankAccountId(@PathVariable int bankAccountId) {
        return transactionService.findAllByAccountId(bankAccountId);
    }

    //Prints last ten transactions having prelievo as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/prelievo/{accountId}/last_ten")
    public List<TransactionDTO> findAllOpByPrelievoLastTen(@PathVariable int accountId){
        return transactionService.findAllByQueryMadePrelievoLastTen(accountId);
    }

    //Prints last twenty transactions having prelievo as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/prelievo/{accountId}/last_twenty")
    public List<TransactionDTO> findAllOpByPrelievoLastTwenty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadePrelievoLastTwenty(accountId);
    }

    //Prints last fifty transactions having prelievo as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/prelievo/{accountId}/last_fifty")
    public List<TransactionDTO> findAllOpByPrelievoLastFifty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadePrelievoLastFifty(accountId);
    }

    //Prints last ten transactions having deposito as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/deposito/{accountId}/last_ten")
    public List<TransactionDTO> findAllOpByDepositoLastTen(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeDepositoLastTen(accountId);
    }

    //Prints last twenty transactions having deposito as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/deposito/{accountId}/last_twenty")
    public List<TransactionDTO> findAllOpByDepositoLastTwenty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeDepositoLastTwenty(accountId);
    }

    //Prints last fifty transactions having deposito as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/deposito/{accountId}/last_fifty")
    public List<TransactionDTO> findAllOpByDepositoLastFifty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeDepositoLastFifty(accountId);
    }

    //Prints last ten transactions having BONIFICO as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/bonifico/{accountId}/last_ten")
    public List<TransactionDTO> findAllOpByBonificoLastTen(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeBonificoLastTen(accountId);
    }

    //Prints last twenty transactions having BONIFICO as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/bonifico/{accountId}/last_twenty")
    public List<TransactionDTO> findAllOpByBonificoLastTwenty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeBonificoLastTwenty(accountId);
    }

    //Prints last fifty transactions having BONIFICO as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/bonifico/{accountId}/last_fifty")
    public List<TransactionDTO> findAllOpByBonificoLastFifty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeBonificoLastFifty(accountId);
    }


    //Prints last ten transactions having RICARICA_TELEFONICA as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/ricarica_telefonica/{accountId}/last_ten")
    public List<TransactionDTO> findAllOpByRicaricaTelefonicaLastTen(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeRicaricaTelefonicaLastTen(accountId);
    }

    //Prints last twenty transactions having RICARICA_TELEFONICA as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/ricarica_telefonica/{accountId}/last_twenty")
    public List<TransactionDTO> findAllOpByRicaricaTelefonicaLastTwenty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeRicaricaTelefonicaLastTwenty(accountId);
    }

    //Prints last fifty transactions having RICARICA_TELEFONICA as op type,
    //taking in a pathVariable of the account id
    @GetMapping("/filter/ricarica_telefonica/{accountId}/last_fifty")
    public List<TransactionDTO> findAllOpByRicaricaTelefonicaLastFifty(@PathVariable int accountId){
        return transactionService.findAllByQueryMadeRicaricaTelefonicaLastFifty(accountId);
    }

    //making a deposit(passing in a positive integer) or a withdrawal(passing in a negative integer)
    //having a body of a new DTO containing
    // amount(double), accountId(int), causal(String)
    //eventually calls rabbitMQ creating a queue, calling account MS to update its balance
    @PostMapping("/make/transaction")
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

        if (response != null && response.get("errorMessage")==2) {

            TransactionDTO transactionDTOFrom = new TransactionDTO(OperationType.BONIFICO,
                    -transferMessageDTO.getAmount(), new Date(), response.get("accountIdFrom") ,
                    transferMessageDTO.getCausal());
            TransactionDTO transactionDTOTo = new TransactionDTO(OperationType.BONIFICO,
                    transferMessageDTO.getAmount(), new Date(), response.get("accountIdTo"),
                    transferMessageDTO.getCausal());
            transactionService.save(transactionDTOFrom);
            transactionService.save(transactionDTOTo);
            return ResponseEntity.ok("bonifico effettuato con successo !");

        }
        else if(response.get("errorMessage")==1) {
            return ResponseEntity.badRequest().body("Errore: Iban destinatario non deve coincidere con il tuo!");
        }else if(response.get("errorMessage")==3) {
            return ResponseEntity.badRequest().body("Errore: Saldo insufficiente per effettuare il bonifico");
        }else if(response.get("errorMessage")==4){
            return ResponseEntity.badRequest().body
                    ("Errore: Il conto associato all'Iban destinatario non è attivo, Contatta il supporto!");
        }else{
            return ResponseEntity.badRequest().body
                    ("Errore: Contatta il supporto!");
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
            TransactionDTO transactionDTO = new TransactionDTO(type, -amount,
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





