package com.bankapi;

import com.bankapi.controllers.ClientController;
import com.bankapi.data.Currency;
import com.bankapi.data.CurrencyConverter;
import com.bankapi.repositories.ConverterRepository;
import com.bankapi.services.ConverterService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ConverterTests {
    @Autowired
    ConverterService converterService;
    @Autowired
    ConverterRepository converterRepository;

    @Test
    void testConvertKZT() throws IOException, JSONException {
        CurrencyConverter currencyConverter = converterRepository.findById("KZT").get();
        double course = currencyConverter.getCourse();
        double value = 1000.0;
        assertTrue("Test converting", (value/course == converterService.convert(Currency.KZT, Double.valueOf(1000))));
    }

    @Test
    void testConvertRUB() throws IOException, JSONException {
        CurrencyConverter currencyConverter = converterRepository.findById("RUB").get();
        double course = currencyConverter.getCourse();
        double value = 1000.0;
        assertTrue("Test converting", (value/course == converterService.convert(Currency.RUB, Double.valueOf(1000))));
    }

    @Test
    void testFindCurrencyKZT() throws IOException, JSONException {
        assertTrue("Test converting", converterService.findCurrency("KZT"));
    }

    @Test
    void testFindCurrencyRUB() throws IOException, JSONException {
        assertTrue("Test converting", converterService.findCurrency("RUB"));
    }
}
