package com.eventmanagement.tickets.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

public interface UserClient {

    @GetExchange("/api/user/verify/{id}")
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackMethod")
    public boolean existUserById(@PathVariable Long id, @RequestHeader Map<String,String> authorization);

    default boolean fallbackMethod(Long id,Map<String,String> authorization, Throwable throwable) {
        return false;
    }
}
