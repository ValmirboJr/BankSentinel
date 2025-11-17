package org.example.banksentinel.service;

import org.example.banksentinel.entity.BankAccount;
import org.example.banksentinel.entity.Transaction;
import org.example.banksentinel.mapper.TransactionMapper;
import org.example.banksentinel.messaging.TransactionProducer;
import org.example.banksentinel.repository.BankAccountRepository;
import org.example.banksentinel.repository.TransactionRepository;
import org.example.banksentinel.request.TransactionRequest;
import org.example.banksentinel.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionProducer  transactionProducer;

    public TransactionResponse createTrn(TransactionRequest request) {
        BankAccount account = bankAccountRepository.findById(request.bank_account_id())
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada"));

        Transaction transaction = TransactionMapper.toTransaction(request);
        transaction.setAccount(account);
        transaction = transactionRepository.save(transaction);
        TransactionResponse response = TransactionMapper.toTransactionResponse(transaction);
        transactionProducer.sendTransactionCreated(response);
        return response;
    }

    public Optional<Transaction> getTrn(Long id) {
        return transactionRepository.findById(id);
    }
    public List<Transaction> getAllTrn() {
        return transactionRepository.findAll();
    }
    public void DeleteTrn(Long id) {
        transactionRepository.deleteById(id);
    }
}
