package com.bankapi.repositories;

import com.bankapi.data.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t WHERE t.limit_exceeded = true AND t.account_from = :id ")
    ArrayList<Transaction> getTransactionsByLimit_exceeded(@Param("id") Long id);
}
