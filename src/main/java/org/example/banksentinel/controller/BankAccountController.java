package org.example.banksentinel.controller;

import lombok.RequiredArgsConstructor;
import org.example.banksentinel.entity.BankAccount;
import org.example.banksentinel.mapper.BankAccountMapper;
import org.example.banksentinel.request.BankAccountRequest;
import org.example.banksentinel.response.BankAccountResponse;
import org.example.banksentinel.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/create")
    public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody BankAccountRequest request) {
        BankAccount bankAccount = bankAccountService.createBankAccount(BankAccountMapper.toBankAccount(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(BankAccountMapper.toBankAccountResponse(bankAccount));
    }
    @GetMapping()
    public ResponseEntity<List<BankAccountResponse>> getBankAccount() {
        return ResponseEntity.ok(bankAccountService.ListAccount()
                .stream().map(BankAccountMapper::toBankAccountResponse)
                .toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> getUniqueBankAccount(@PathVariable Long id) {
        return ResponseEntity.of(bankAccountService.findByAccount(id)
                .map(BankAccountMapper::toBankAccountResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
