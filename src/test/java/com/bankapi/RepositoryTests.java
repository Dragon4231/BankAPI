package com.bankapi;

import com.bankapi.data.CurrencyConverter;
import com.bankapi.data.Limit;
import com.bankapi.data.Transaction;
import com.bankapi.repositories.ConverterRepository;
import com.bankapi.repositories.LimitRepository;
import com.bankapi.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertTrue;

@SpringBootTest
class RepositoryTests {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    LimitRepository limitRepository;
    @Autowired
    ConverterRepository converterRepository;

    @Test
    void testAddTransaction() {
        Transaction transaction1 =  transactionRepository.save(new Transaction());
        assertTrue("Test adding",transactionRepository.existsById(transaction1.getID()));
        transactionRepository.deleteById(transaction1.getID());
    }

    @Test
    void testAddCurrency(){
        CurrencyConverter currencyConverter = new CurrencyConverter();
        currencyConverter.setNameOfCurrency("TST");
        converterRepository.save(currencyConverter);
        assertTrue("Test adding",converterRepository.existsById(currencyConverter.getNameOfCurrency()));
        converterRepository.deleteById("TST");
    }

    @Test
    void testAddLimit(){
        Limit limit = new Limit();
        limit.setAccount_id(new Long(-1));
        limitRepository.save(limit);
        assertTrue("Test adding",limitRepository.existsById(limit.getAccount_id()));
        limitRepository.deleteById(limit.getAccount_id());
    }

    @Test
    void testUpdatingLimitProduct(){
        Limit limit = new Limit();
        limit.setAccount_id(new Long(-1));
        limit.setLimitForProduct(Double.valueOf(15.0));
        limitRepository.save(limit);
        limitRepository.updateLimitProduct(limit.getAccount_id(),Double.valueOf(1000), new GregorianCalendar());
        assertTrue("Test updating",(limitRepository.findById(limit.getAccount_id()).get().getLimitForProduct().equals(Double.valueOf(1000))));
        limitRepository.deleteById(limit.getAccount_id());
    }

    @Test
    void testUpdatingLimitServices(){
        Limit limit = new Limit();
        limit.setAccount_id(new Long(-1));
        limit.setLimitForServices(Double.valueOf(15.0));
        limitRepository.save(limit);
        limitRepository.updateLimitServices(limit.getAccount_id(),Double.valueOf(1000), new GregorianCalendar());
        assertTrue("Test updating",(limitRepository.findById(limit.getAccount_id()).get().getLimitForServices().equals(Double.valueOf(1000))));
        limitRepository.deleteById(limit.getAccount_id());
    }

}
