package org.example.banksentinel.messaging;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.banksentinel.response.TransactionResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public void sendTransactionNotification(TransactionResponse transaction, String Email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(Email);
            message.setSubject("🏦 BankSentinel - New Transaction");
            message.setText(buildTransactionMessage(transaction));

            mailSender.send(message);

            log.info("Email invited: {}", Email);

        } catch (Exception e) {
            log.error("Erro envited email: {}", e.getMessage(), e);
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