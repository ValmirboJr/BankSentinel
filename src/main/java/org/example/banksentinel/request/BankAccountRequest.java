package org.example.banksentinel.request;

import lombok.Builder;
import org.example.banksentinel.enums.Banks;
import org.example.banksentinel.enums.CurrencyType;
import org.example.banksentinel.enums.TypeAccount;

import java.time.LocalDateTime;

@Builder
public record BankAccountRequest(Banks bank, String accountNumber, TypeAccount typeAccount, CurrencyType currency, double AccountBalance,
                                 LocalDateTime registrationDate) {
}
