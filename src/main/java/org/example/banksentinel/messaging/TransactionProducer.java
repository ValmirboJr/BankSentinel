package org.example.banksentinel.messaging;

import lombok.RequiredArgsConstructor;
import org.example.banksentinel.config.RabbitMQConfig;
import org.example.banksentinel.response.TransactionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendTransactionCreated(TransactionResponse transaction) {
        log.info("Sending to queue: {}", transaction);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.TRANSACTION_EXCHANGE,
                RabbitMQConfig.TRANSACTION_ROUTING_KEY,
                transaction
        );

        log.info("Transaction sucessul!");
    }
}
