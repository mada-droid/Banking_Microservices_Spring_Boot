package com.example.transactionmicroservice.transactionmicroservice.service;


import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/* Devo inviare i dati della transizione
(id_account associato, tipo di operazione e importo dell'operazione)
all'account per fare l'aggiornamento del saldo */

@Service
public class AmountMessageSender {

    @Autowired
    private Queue queue;
    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AmountMessageSender.class);

    public AmountMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Boolean sendAmountMessage(AmountMessageDTO amountMessageDTO) {
        logger.info("Messaggio inviato");
        AmountMessageDTO messageDTO = new AmountMessageDTO(amountMessageDTO.getAmount(),amountMessageDTO.getAccountId());
        Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive(queue.getName(),messageDTO);
        return response;
    }
}


