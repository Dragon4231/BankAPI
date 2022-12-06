package com.bankapi.api;

import com.bankapi.data.Currency;
import com.bankapi.data.CurrencyConverter;
import com.bankapi.repositories.ConverterRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.GregorianCalendar;

@Service
public class OpenExchangeRates {
    @Autowired
    ConverterRepository converterRepository;

    private final String id = "46b37a4f5f4142d1bff0b2f18814fb92";
    HttpURLConnection connection;
    URL url;

    @Scheduled(cron = "@daily")
    public void updateInfo() throws IOException, JSONException {
        String urlReq = new String("https://openexchangerates.org/api/latest.json?app_id="+id);
        url = new URL(urlReq);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream stream;
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            stream = connection.getInputStream();
            String temp = convertStreamToString(stream);
            makeNewCurrency(temp);
        }
    }

    private void makeNewCurrency(String json) throws JSONException {
        JSONObject allInfo = new JSONObject(json);
        JSONObject rates = allInfo.getJSONObject("rates");
        for(Currency c : Currency.values()){
            Double value = rates.getDouble(c.toString());
            CurrencyConverter currencyConverter = new CurrencyConverter();
            currencyConverter.setNameOfCurrency(c.toString());
            currencyConverter.setLastUpdate(new GregorianCalendar());
            currencyConverter.setCourse(value);
            if(converterRepository.existsById(currencyConverter.getNameOfCurrency())){
                converterRepository.deleteById(c.toString());
            }
            converterRepository.save(currencyConverter);
        }
    }

    private String convertStreamToString(InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        stream.close();

        return sb.toString();
    }
}
