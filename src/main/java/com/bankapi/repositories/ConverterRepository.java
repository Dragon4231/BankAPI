package com.bankapi.repositories;

import com.bankapi.data.CurrencyConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConverterRepository extends JpaRepository<CurrencyConverter,String> {

}
