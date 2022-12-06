package com.bankapi.services;

import com.bankapi.data.Category;
import com.bankapi.data.Limit;
import com.bankapi.data.Transaction;
import com.bankapi.repositories.ConverterRepository;
import com.bankapi.repositories.LimitRepository;
import com.bankapi.repositories.TransactionRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TransactionService {
    @Autowired
    ConverterService converterService;
    @Autowired
    LimitRepository limitRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ConverterRepository converterRepository;

    public void startTransaction(Transaction transaction) throws CloneNotSupportedException, JSONException, IOException {
        Transaction newTransaction = transaction.clone();
        Double newValue = converterService.convert(newTransaction.getCurrency_shortname(), newTransaction.getSum());
        newTransaction.setSum(newValue);
        if (!limitRepository.existsById(newTransaction.getAccount_to()) || !limitRepository.existsById(newTransaction.getAccount_from())) {
            throw new IllegalArgumentException();
        }
        if (newTransaction.getExpense_category() == Category.PRD) {
            Long idFrom = newTransaction.getAccount_from();
            limitRepository.updateExpenseProduct(idFrom, newTransaction.getSum());
            Limit temp = limitRepository.findById(idFrom).get();
            if (temp.getLimitForProduct() - temp.getExpensesProduct() < 0) newTransaction.setLimit_exceeded(true);
        } else if (newTransaction.getExpense_category() == Category.SRV) {
            Long idFrom = newTransaction.getAccount_from();
            limitRepository.updateExpenseServices(idFrom, newTransaction.getSum());
            Limit temp = limitRepository.findById(idFrom).get();
            if (temp.getLimitForServices() - temp.getExpensesServices() < 0) newTransaction.setLimit_exceeded(true);
        }
        transactionRepository.save(newTransaction);
    }
}
