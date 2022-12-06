package com.bankapi.repositories;

import com.bankapi.data.Limit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;

public interface LimitRepository extends JpaRepository<Limit, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Limit l SET l.limitForProduct = :productLimit, l.dateForProductLimit = :cal WHERE l.account_id = :id")
    void updateLimitProduct(@Param("id") Long id, @Param("productLimit") Double productLimit, @Param("cal") Calendar calendar);

    @Transactional
    @Modifying
    @Query("UPDATE Limit l SET l.limitForServices = :limitForServices, l.dateForServicesLimit = :cal WHERE l.account_id = :id")
    void updateLimitServices(@Param("id") Long id, @Param("limitForServices") Double servicesLimit, @Param("cal") Calendar calendar);

    @Transactional
    @Modifying
    @Query("UPDATE Limit l SET l.expensesProduct = (l.expensesProduct + :expense) WHERE l.account_id = :id")
    void updateExpenseProduct(@Param("id") Long id, @Param("expense") Double expense);

    @Transactional
    @Modifying
    @Query("UPDATE Limit l SET l.expensesServices = (l.expensesServices + :expense) WHERE l.account_id = :id")
    void updateExpenseServices(@Param("id") Long id, @Param("expense") Double expense);
}
