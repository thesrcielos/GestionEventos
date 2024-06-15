package com.eventmanagement.tickets.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserClient {

    @GetExchange("/api/user/verify/{id}")
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackMethod")
    public boolean existUserById(@PathVariable Long id);

    default boolean fallbackMethod(Long id, Throwable throwable) {
        return false;
    }
}
