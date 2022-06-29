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

        if(amountMessageDTO.getAmount()<0){
            if (bankAccountDTO.getBalance() + amountMessageDTO.getAmount() >= 0 && bankAccountDTO.getState().equals(State.ACTIVE)) {
                bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amountMessageDTO.getAmount());
                bankAccountService.update(bankAccountDTO);
                return true;
            } else {
                return false;
            }
        }else{
            if (bankAccountDTO.getState().equals(State.ACTIVE)) {
                bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amountMessageDTO.getAmount());
                bankAccountService.update(bankAccountDTO);
                return true;
            } else {
                return false;
            }

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

        TransferResponseDTO transferResponseDTOs = new TransferResponseDTO();

        //setErrorMessage (5) riferisce a {Iban Destinatario inesistente!!}
        if(bankAccountDTOTo == null){
            transferResponseDTOs.setErrorMessage(5);
            return transferResponseDTOs;
        }

        TransferResponseDTO transferResponseDTO =
                new TransferResponseDTO(bankAccountDTOFrom.getId(),bankAccountDTOTo.getId());

            if(transferMessageDTO.getOriginIban().equals(transferMessageDTO.getDestinationIban())) {
                //setErrorMessage (1) riferisce a {Iban Origin coincide col quello del Destinatario!}
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
                //setErrorMessage (2) riferisce a {Nessuna sorta di errore, bonifico effettuato correttamente!}
                transferResponseDTO.setErrorMessage(2);
                return  transferResponseDTO;
            } else if(bankAccountDTOFrom.getBalance() - transferMessageDTO.getAmount() < 0){
                //setErrorMessage (3) riferisce a {Saldo insufficiente!!}
                transferResponseDTO.setErrorMessage(3);
                return transferResponseDTO;
            }
            else if(!(bankAccountDTOTo.getState().equals(State.ACTIVE))){
                //setErrorMessage (4) riferisce a {Conto non è ancora attivo !}
                transferResponseDTO.setErrorMessage(4);
                return transferResponseDTO;
            }else{
                return null;
            }
        }
    }


