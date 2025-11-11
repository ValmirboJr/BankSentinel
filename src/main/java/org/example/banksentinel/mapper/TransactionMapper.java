package org.example.banksentinel.mapper;

import lombok.experimental.UtilityClass;
import org.example.banksentinel.entity.BankAccount;
import org.example.banksentinel.entity.Transaction;
import org.example.banksentinel.request.TransactionRequest;
import org.example.banksentinel.response.TransactionResponse;

@UtilityClass
public class TransactionMapper {

    public static Transaction toTransaction(TransactionRequest transactionRequest){

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccount.getId());

        return Transaction
                .builder()
                .eventTime(transactionRequest.eventTime())
                .amount(transactionRequest.amount())
                .paymentMethod(transactionRequest.paymentMethod())
                .description(transactionRequest.description())
                .mcc(transactionRequest.mcc())
                .category(transactionRequest.category())
                .build();
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction){

            return TransactionResponse
                    .builder()
                    .id(transaction.getId())
                    .bank_account_id(transaction.getAccount().getId())
                    .eventTime(transaction.getEventTime())
                    .amount(transaction.getAmount())
                    .paymentMethod(transaction.getPaymentMethod())
                    .description(transaction.getDescription())
                    .mcc(transaction.getMcc())
                    .category(transaction.getCategory())
                    .build();
        }
}
