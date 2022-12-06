package com.bankapi.controllers;

import com.bankapi.api.OpenExchangeRates;
import com.bankapi.data.Transaction;
import com.bankapi.repositories.TransactionRepository;
import com.bankapi.services.TransactionService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/transaction")
public class ReceiveTransactionController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionService transactionService;
    @Autowired
    OpenExchangeRates openExchangeRates;

    @PostMapping("/newTransaction")
    @ResponseStatus
    private ResponseEntity makeTransaction(@RequestBody Transaction transaction) throws CloneNotSupportedException, JSONException, IOException {
        transactionService.startTransaction(transaction);
        return new ResponseEntity("Transaction was successful", HttpStatus.OK);
    }
}
