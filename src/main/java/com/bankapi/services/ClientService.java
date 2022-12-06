package com.bankapi.services;

import com.bankapi.data.Transaction;
import com.bankapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientService {
    TransactionRepository transactionRepository;

    @Autowired
    public ClientService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ArrayList<Transaction> getLimitExceededTransactions(Long idFind) {
        return transactionRepository.getTransactionsByLimit_exceeded(idFind);
    }
}
