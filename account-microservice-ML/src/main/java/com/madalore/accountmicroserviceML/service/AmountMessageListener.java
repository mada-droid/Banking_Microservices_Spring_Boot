package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.*;
import com.madalore.accountmicroserviceML.entity.State;
import com.madalore.accountmicroserviceML.utils.BankAccountUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AmountMessageListener {

    BankAccountService bankAccountService;

    public AmountMessageListener(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @RabbitListener(queues = "myQueue")
    public Boolean receive(AmountMessageDTO amountMessageDTO) {

        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(amountMessageDTO.getAccountId());

        if (bankAccountDTO.getBalance() - amountMessageDTO.getAmount() >= 0 && bankAccountDTO.getState().equals(State.ACTIVE)) {
            bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amountMessageDTO.getAmount());
            bankAccountService.update(bankAccountDTO);
            return true;
        } else {
            return false;
        }
    }

    @RabbitListener(queues = "newAccountQueue")
    public Boolean receive(NewAccountMessageDTO newAccountMessageDTO) {
        try {
            BankAccountUtils bankAccountUtils = new BankAccountUtils();

            BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccountUtils.generateIban(),
                    State.ACTIVATION_REQUEST, 0.00, newAccountMessageDTO.getUserId(), bankAccountUtils.getAccountNumber());

            bankAccountService.save(bankAccountDTO);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @RabbitListener(queues = "transferQueue")
    public Object receiveTransfer(TransferMessageDTO transferMessageDTO) {

        BankAccountDTO bankAccountDTOFrom = bankAccountService.findBankAccountByIban(transferMessageDTO.getOriginIban());
        BankAccountDTO bankAccountDTOTo = bankAccountService.findBankAccountByIban(transferMessageDTO.getDestinationIban());

        TransferResponseDTO transferResponseDTO =
                new TransferResponseDTO(bankAccountDTOFrom.getId(),bankAccountDTOTo.getId());

            if(transferMessageDTO.getOriginIban().equals(transferMessageDTO.getDestinationIban())) {
                transferResponseDTO.setErrorMessage(1);
                return transferResponseDTO;
            }

            if (bankAccountDTOFrom.getBalance() - transferMessageDTO.getAmount() >= 0
                    && bankAccountDTOFrom.getState().equals(State.ACTIVE)
                    && bankAccountDTOTo.getState().equals(State.ACTIVE)) {
                bankAccountDTOFrom.setBalance(bankAccountDTOFrom.getBalance() - transferMessageDTO.getAmount());
                bankAccountDTOTo.setBalance(bankAccountDTOTo.getBalance() + transferMessageDTO.getAmount());
                bankAccountService.update(bankAccountDTOFrom);
                bankAccountService.update(bankAccountDTOTo);
                transferResponseDTO.setErrorMessage(2);
                return  transferResponseDTO;
            } else if(bankAccountDTOFrom.getBalance() - transferMessageDTO.getAmount() < 0){
                transferResponseDTO.setErrorMessage(3);
                return transferResponseDTO;
            }
            else if(!(bankAccountDTOTo.getState().equals(State.ACTIVE))){
                transferResponseDTO.setErrorMessage(4);
                return transferResponseDTO;
            }else{
                return null;
            }
        }
    }


