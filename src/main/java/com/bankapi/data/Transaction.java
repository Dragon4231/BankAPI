package com.bankapi.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

@Data
@Entity
public class Transaction implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;

    long account_from;

    long account_to;

    double sum;

    Currency currency_shortname;

    Category expense_category;

    boolean limit_exceeded = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Calendar date;

    @Override
    public Transaction clone() throws CloneNotSupportedException {
        return (Transaction)super.clone();
    }

}