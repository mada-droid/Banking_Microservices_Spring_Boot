package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.*;
import com.madalore.accountmicroserviceML.entity.State;
import com.madalore.accountmicroserviceML.utils.BankAccountUtils;
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

        if (bankAccountDTO.getBalance() + amountMessageDTO.getAmount() >= 0 && bankAccountDTO.getState().equals(State.ACTIVE)) {
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

        try {
            if(transferMessageDTO.getOriginIban().equals(transferMessageDTO.getDestinationIban())){
                throw new RuntimeException("Non puoi fare un bonifico a te stesso!!");
            }

            BankAccountDTO bankAccountDTOFrom = bankAccountService.findBankAccountByIban(transferMessageDTO.getOriginIban());
            BankAccountDTO bankAccountDTOTo = bankAccountService.findBankAccountByIban(transferMessageDTO.getDestinationIban());

            if (bankAccountDTOFrom.getBalance() - transferMessageDTO.getAmount() >= 0
                    && bankAccountDTOFrom.getState().equals(State.ACTIVE)
                    && bankAccountDTOTo.getState().equals(State.ACTIVE)
                    && transferMessageDTO.getAmount() > 0) {
                bankAccountDTOFrom.setBalance(bankAccountDTOFrom.getBalance() - transferMessageDTO.getAmount());
                bankAccountDTOTo.setBalance(bankAccountDTOTo.getBalance() + transferMessageDTO.getAmount());
                bankAccountService.update(bankAccountDTOFrom);
                bankAccountService.update(bankAccountDTOTo);
                TransferResponseDTO transferResponseDTO =
                        new TransferResponseDTO(bankAccountDTOFrom.getId(),bankAccountDTOTo.getId());
                return  transferResponseDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}


