package com.example.demo.service;


import com.example.demo.dto.NewAccountMessageDTO;
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
public class NewAccountMessageSender {

    @Autowired
    private Queue queue;
    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(NewAccountMessageSender.class);

    public NewAccountMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Boolean sendAccountMessage(NewAccountMessageDTO newAccountMessageDTO) {
        logger.info("Messaggio inviato");

        Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive("newAccountQueue",newAccountMessageDTO);
        return response;
    }
}


