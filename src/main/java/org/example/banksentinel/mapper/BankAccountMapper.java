package org.example.banksentinel.mapper;

import lombok.experimental.UtilityClass;
import org.example.banksentinel.entity.BankAccount;
import org.example.banksentinel.request.BankAccountRequest;
import org.example.banksentinel.response.BankAccountResponse;

@UtilityClass
public class BankAccountMapper {

    public static BankAccount toBankAccount(BankAccountRequest bankAccountRequest) {
        return BankAccount
                .builder()
                .banks(bankAccountRequest.bank())
                .accountNumber(bankAccountRequest.accountNumber())
                .typeAccount(bankAccountRequest.typeAccount())
                .currency(bankAccountRequest.currency())
                .AccountBalance(bankAccountRequest.AccountBalance())
                .registrationDate(bankAccountRequest.registrationDate())
                .build();
    }
    public static BankAccountResponse toBankAccountResponse(BankAccount bankAccount) {
        return BankAccountResponse
                .builder()
                .id(bankAccount.getId())
                .banks(bankAccount.getBanks())
                .accountNumber(bankAccount.getAccountNumber())
                .typeAccount(bankAccount.getTypeAccount())
                .currency(bankAccount.getCurrency())
                .AccountBalance(bankAccount.getAccountBalance())
                .registrationDate(bankAccount.getRegistrationDate())
                .build();
    }
}
