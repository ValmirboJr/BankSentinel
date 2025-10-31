package org.example.banksentinel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.banksentinel.enums.Banks;
import org.example.banksentinel.enums.CurrencyType;
import org.example.banksentinel.enums.TypeAccount;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_account")
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Banks banks;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @Column(nullable = false)
    private double AccountBalance;

    @CreationTimestamp
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
}
