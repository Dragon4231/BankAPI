package com.bankapi.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
@Entity
public class CurrencyConverter {
    @Id
    String nameOfCurrency;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Calendar lastUpdate = new GregorianCalendar();

    Double course;
}
