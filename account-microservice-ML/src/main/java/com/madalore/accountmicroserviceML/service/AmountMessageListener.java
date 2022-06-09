package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.AmountMessageDTO;
import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AmountMessageListener {

    BankAccountService bankAccountService;

    public AmountMessageListener(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @RabbitListener(queues = "myQueue")
    public Boolean receive(AmountMessageDTO amountMessageDTO) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(amountMessageDTO.getAccountId());

        if (bankAccountDTO.getBalance() + amountMessageDTO.getAmount() < 0) {
            return false;
        }else {
            bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amountMessageDTO.getAmount());
            bankAccountService.update(bankAccountDTO);
        }
        return true;
    }
}

