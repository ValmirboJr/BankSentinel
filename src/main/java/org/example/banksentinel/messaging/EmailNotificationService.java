package org.example.banksentinel.messaging;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.example.banksentinel.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SmsNotificationService {

    @Value("${spring.datasource.twilio.phone-from}")
    private String phoneFrom;

    public void sendTransactionNotification(TransactionResponse transaction, String customerPhone) {
        try {
            String messageBody = buildTransactionMessage(transaction);

            Message message = Message.creator(
                    new PhoneNumber(customerPhone),
                    new PhoneNumber(phoneFrom),
                    messageBody
            ).create();

            log.info("SMS send to sucess! SID: {}", message.getSid());

        } catch (Exception e) {
            log.error("Error to send WhatsApp: {}", e.getMessage(), e);
        }
    }

    private String buildTransactionMessage(TransactionResponse transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return String.format("""
            🏦 *BankSentinel - New Transaction*
            
            💰 price: R$ %.2f
            📝 Description: %s
            💳 Method: %s
            📂 Category: %s
            🕐 Date and time: %s
            
            ✅ Transaction Processed!
            """,
                transaction.amount(),
                transaction.description(),
                transaction.paymentMethod(),
                transaction.category(),
                transaction.eventTime().format(formatter)
        );
    }
}