package org.example.banksentinel.request;

import lombok.Builder;
import org.example.banksentinel.entity.Transaction;
import org.example.banksentinel.enums.Banks;
import org.example.banksentinel.enums.CurrencyType;
import org.example.banksentinel.enums.TypeAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BankAccountRequest(Banks bank, String accountNumber, TypeAccount typeAccount, CurrencyType currency, BigDecimal accountBalance,
                                 LocalDateTime registrationDate, List<Transaction> transactions) {
}
