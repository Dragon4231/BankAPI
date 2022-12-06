package com.bankapi.services;

import com.bankapi.api.OpenExchangeRates;
import com.bankapi.data.Currency;
import com.bankapi.data.CurrencyConverter;
import com.bankapi.repositories.ConverterRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConverterService {
    @Autowired
    ConverterRepository converterRepository;
    @Autowired
    OpenExchangeRates openExchangeRates;

    public double convert(Currency currency, Double value) throws JSONException, IOException {
        if(!converterRepository.existsById(currency.toString())){
            if(!findCurrency(currency.toString())) throw new IllegalArgumentException();
        }
        CurrencyConverter currencyConverter = converterRepository.findById(currency.toString()).get();
        return value/currencyConverter.getCourse();
    }

    public boolean findCurrency(String id) throws JSONException, IOException {
        openExchangeRates.updateInfo();
        if(converterRepository.existsById(id)) return true;
        return false;
    }

}
