package org.example.banksentinel.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.banksentinel.config.RabbitMQConfig;
import org.example.banksentinel.entity.BankAccount;
import org.example.banksentinel.repository.BankAccountRepository;
import org.example.banksentinel.response.TransactionResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionConsumer {

    private final EmailNotificationService emailService;
    private final BankAccountRepository bankAccountRepository;

    @RabbitListener(queues = RabbitMQConfig.TRANSACTION_QUEUE)
    public void receiveTransaction(TransactionResponse transaction) {
        log.info("Transaction in queue: {}", transaction);
        log.info("Looking for account with ID: {}", transaction.bank_account_id());

        BankAccount account = bankAccountRepository.findById(transaction.bank_account_id())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        log.info("Account found - ID: {}, Email: {}", account.getId(), account.getEmail());

        if (account.getEmail() != null) {
            log.info("Sending email to: {}", account.getEmail());
            emailService.sendTransactionNotification(transaction, account.getEmail());
        } else {
            log.warn("Customer email not found for account ID: {}", account.getId());
        }

        log.info("Process Complete");
    }
}