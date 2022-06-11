package com.example.transactionmicroservice.transactionmicroservice.rest;

import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransferMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.service.AmountMessageSender;
import com.example.transactionmicroservice.transactionmicroservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Messager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {


    private TransactionService transactionService;

    @Autowired
    private AmountMessageSender amountMessageSender;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{bankAccountId}/transactions")
    public List<TransactionDTO> findAllByBankAccountId(@PathVariable int bankAccountId) {
        return transactionService.findAllByAccountId(bankAccountId);
    }

    @GetMapping("/{operationType}/transactions")
    public List<TransactionDTO> findAllByOpType(@PathVariable OperationType operationType) {
        return transactionService.findAllByOperationType(operationType);
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> addTransaction(@RequestBody AmountMessageDTO amountMessageDTO) {

        if (amountMessageDTO.getAmount() == 0) {
            return ResponseEntity.badRequest().body("L'importo non può essere 0");
        }
        boolean response = transactionService.sendAmountOp(amountMessageDTO);
        if (response) {
                OperationType type;
                if (amountMessageDTO.getAmount() > 0) {
                    type = OperationType.DEPOSIT;
                } else {
                    type = OperationType.WITHDRAWAL;
                }
                TransactionDTO transactionDTO = new TransactionDTO(type, amountMessageDTO.getAmount(),
                        new Date(), amountMessageDTO.getAccountId(),amountMessageDTO.getCausal());
                transactionService.save(transactionDTO);
                return ResponseEntity.ok("Transizione salvata correttamente!!!");
        }
        else {
            return ResponseEntity.badRequest().body("L'importo è troppo elevato!");
        }
    }


    @PostMapping("/transfer")
    public ResponseEntity<?> addTransfer(@RequestBody TransferMessageDTO transferMessageDTO) {

        if (transferMessageDTO.getAmount() == 0) {
            return ResponseEntity.badRequest().body("L'importo non può essere 0");
        }
        HashMap<String,Integer> response = (HashMap<String, Integer>)
                amountMessageSender.sendTransferMessage(transferMessageDTO);
        if (response != null) {

            TransactionDTO transactionDTOFrom = new TransactionDTO(OperationType.WITHDRAWAL,
                    transferMessageDTO.getAmount(), new Date(), response.get("accountIdFrom"),
                    transferMessageDTO.getCausal());

            TransactionDTO transactionDTOTo = new TransactionDTO(OperationType.DEPOSIT,
                    transferMessageDTO.getAmount(), new Date(), response.get("accountIdTo"),
                    transferMessageDTO.getCausal());

            transactionService.save(transactionDTOFrom);
            transactionService.save(transactionDTOTo);

            return ResponseEntity.ok("Transizione salvata correttamente!!!");
        }
        else {
            return ResponseEntity.badRequest().body("Errore!");
        }
    }

    @GetMapping("/all_operations")
    public List<TransactionDTO> getLastTenOperations(){
        return transactionService.findAllByIdIsNotNullOrderByIdDesc();
    }

    @GetMapping("/last_ten_operation/{accountId}")
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc(@PathVariable int accountId){
        return transactionService.findAllByAccountIdOrderByIdDesc(accountId);
    }
}





