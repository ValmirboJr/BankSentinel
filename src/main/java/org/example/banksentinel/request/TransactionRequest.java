package org.example.banksentinel.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.example.banksentinel.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionRequest(Long bank_account_id, @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")LocalDateTime eventTime, BigDecimal amount,
                                 @JsonInclude PaymentMethod paymentMethod,String description, Integer mcc,String category)
{
}
