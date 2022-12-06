package com.bankapi.services;

import com.bankapi.data.Limit;
import com.bankapi.repositories.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public class LimitService {
    @Autowired
    LimitRepository limitRepository;

    public void updateLimit(Limit limit) {
        if(limit.getAccount_id() == null || !limitRepository.existsById(limit.getAccount_id())){
            throw new IllegalArgumentException();
        }
        if(limit.getLimitForServices() != null && limit.getLimitForServices() > 0) limitRepository.updateLimitServices(limit.getAccount_id(), limit.getLimitForServices(), new GregorianCalendar());
        if(limit.getLimitForProduct() != null && limit.getLimitForProduct() > 0) limitRepository.updateLimitProduct(limit.getAccount_id(), limit.getLimitForProduct(), new GregorianCalendar());
    }

}
