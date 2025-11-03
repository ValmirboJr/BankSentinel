package org.example.banksentinel.response;

import lombok.Builder;
import org.example.banksentinel.enums.Banks;
import org.example.banksentinel.enums.CurrencyType;
import org.example.banksentinel.enums.TypeAccount;

import java.time.LocalDateTime;

@Builder
public record BankAccountResponse(Long id, Banks banks, String accountNumber, TypeAccount typeAccount, CurrencyType currency, double AccountBalance,
                                  LocalDateTime registrationDate) {
}
