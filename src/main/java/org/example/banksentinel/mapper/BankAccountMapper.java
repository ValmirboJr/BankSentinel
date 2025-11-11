package org.example.banksentinel.mapper;

import lombok.experimental.UtilityClass;
import org.example.banksentinel.entity.BankAccount;
import org.example.banksentinel.entity.Transaction;
import org.example.banksentinel.request.BankAccountRequest;
import org.example.banksentinel.response.BankAccountResponse;
import org.example.banksentinel.response.TransactionResponse;

import java.util.List;

@UtilityClass
public class BankAccountMapper {

    public static BankAccount toBankAccount(BankAccountRequest bankAccountRequest) {

        List<Transaction> transactions = bankAccountRequest.transactions().stream()
                .map(transactionid -> Transaction.builder().id(transactionid.getId()).build())
                .toList();

        return BankAccount
                .builder()
                .banks(bankAccountRequest.bank())
                .accountNumber(bankAccountRequest.accountNumber())
                .typeAccount(bankAccountRequest.typeAccount())
                .currency(bankAccountRequest.currency())
                .accountBalance(bankAccountRequest.accountBalance())
                .registrationDate(bankAccountRequest.registrationDate())
                .transactions(transactions)
                .build();
    }
    public static BankAccountResponse toBankAccountResponse(BankAccount bankAccount) {

        List<TransactionResponse> transactions = bankAccount.getTransactions()
                .stream()
                .map(TransactionMapper::toTransactionResponse)
                .toList();

        return BankAccountResponse
                .builder()
                .id(bankAccount.getId())
                .banks(bankAccount.getBanks())
                .accountNumber(bankAccount.getAccountNumber())
                .typeAccount(bankAccount.getTypeAccount())
                .currency(bankAccount.getCurrency())
                .accountBalance(bankAccount.getAccountBalance())
                .registrationDate(bankAccount.getRegistrationDate())
                .transactions(transactions)
                .build();
    }
}
