package org.example.banksentinel.repository;

import org.example.banksentinel.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    Optional<BankAccount> findByAccountNumber(String accountNumber);

    Optional<BankAccount> getBankAccountsById(Long id);
}
