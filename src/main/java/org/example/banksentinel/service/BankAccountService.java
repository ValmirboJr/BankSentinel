package org.example.banksentinel.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.banksentinel.entity.BankAccount;
import org.example.banksentinel.repository.BankAccountRepository;
import org.example.banksentinel.request.BankAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(BankAccount bankAccount) {
       if (bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber()).isPresent()) {
           return bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber()).get();
       }
        return bankAccountRepository.save(bankAccount);
    }
    public Optional<BankAccount> findByAccount(long id) {
        return bankAccountRepository.findById(id);
    }
    public List<BankAccount> ListAccount() {
        return bankAccountRepository.findAll();
    }
    public Optional<BankAccount> updateBankAccount(long id, BankAccount bankAccountUpdate) {
        Optional<BankAccount> bankUpdate = bankAccountRepository.getBankAccountsById(id);
        if (bankUpdate.isPresent()) {
            BankAccount bankAccount = bankUpdate.get();
            bankAccount.setAccountBalance(bankAccountUpdate.getAccountBalance());
            bankAccount.setTypeAccount(bankAccountUpdate.getTypeAccount());
            bankAccountRepository.save(bankAccount);
            return Optional.of(bankAccount);
        }
        return Optional.empty();
    }
    public void deleteBankAccount(long id) {
        bankAccountRepository.deleteById(id);
    }
}
