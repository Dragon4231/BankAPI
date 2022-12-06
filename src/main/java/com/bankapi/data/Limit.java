package com.bankapi.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
@Entity
@Table(name = "limit_account")
public class Limit {

    @Id
    Long account_id;

    @Column(nullable = true)
    Double limitForProduct;

    @Column(nullable = true)
    Double limitForServices;

    @Column(nullable = true)
    Double expensesProduct;
    @Column(nullable = true)
    Double expensesServices;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Calendar dateForProductLimit = new GregorianCalendar();
    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Calendar dateForServicesLimit = new GregorianCalendar();

}
