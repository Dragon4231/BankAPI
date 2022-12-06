package com.bankapi.controllers;


import com.bankapi.data.Limit;
import com.bankapi.data.Transaction;
import com.bankapi.services.ClientService;
import com.bankapi.services.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    LimitService limitService;
    ClientService clientService;

    @Autowired
    public ClientController(LimitService limitService, ClientService clientService) {
        this.limitService = limitService;
        this.clientService = clientService;
    }

    @PostMapping("/newLimit")
    @ResponseStatus
    private ResponseEntity changeLimit(@RequestBody Limit limit) {
        limitService.updateLimit(limit);
        return new ResponseEntity("Limit is updated", HttpStatus.OK);
    }

    @GetMapping("/get/{idFind}")
    private String getTransactions(@PathVariable Long idFind) {
        ArrayList<Transaction> transactions = clientService.getLimitExceededTransactions(idFind);
        if(!transactions.isEmpty()) return transactions.toString();
        return "No transaction by this ID";
    }

}
