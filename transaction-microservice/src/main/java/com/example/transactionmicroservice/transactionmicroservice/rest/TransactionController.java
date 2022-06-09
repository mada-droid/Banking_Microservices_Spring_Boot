package com.example.transactionmicroservice.transactionmicroservice.rest;

import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;
import com.example.transactionmicroservice.transactionmicroservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Messager;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {


    private TransactionService transactionService;

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

    @GetMapping("/{amount}/transactions")
    public List<TransactionDTO> findAllByAmountOp(@PathVariable double amount) {
        return transactionService.findAllByAmount(amount);
    }

    @GetMapping("/{date}/transactions")
    public List<TransactionDTO> findAllByDateOp(@PathVariable Date date) {
        return transactionService.findAllByDate(date);
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
                TransactionDTO transactionDTO = new TransactionDTO(type, amountMessageDTO.getAmount(), new Date(), amountMessageDTO.getAccountId());
                transactionService.save(transactionDTO);
                return ResponseEntity.ok("Transizione salvata correttamente!!!");
        }
        else {
            return ResponseEntity.badRequest().body("L'importo è troppo elevato!");
        }
    }
}





