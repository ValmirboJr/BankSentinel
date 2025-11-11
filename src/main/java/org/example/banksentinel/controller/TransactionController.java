package org.example.banksentinel.controller;

import org.example.banksentinel.entity.Transaction;
import org.example.banksentinel.mapper.BankAccountMapper;
import org.example.banksentinel.mapper.TransactionMapper;
import org.example.banksentinel.request.TransactionRequest;
import org.example.banksentinel.response.BankAccountResponse;
import org.example.banksentinel.response.TransactionResponse;
import org.example.banksentinel.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trn")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<TransactionResponse> register(@RequestBody TransactionRequest request){
        return ResponseEntity.ok(transactionService.createTrn(request));
    }
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTrn(){
        return ResponseEntity.ok(transactionService.getAllTrn()
                .stream().map(TransactionMapper::toTransactionResponse).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTrn(@PathVariable Long id) {
        return ResponseEntity.of(transactionService.getTrn(id)
                .map(TransactionMapper::toTransactionResponse));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTrn(@PathVariable Long id){
        transactionService.DeleteTrn(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
