package com.eventmanagement.tickets.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface EventClient {
    @GetExchange("/api/event/verify/{id}")
    @CircuitBreaker(name = "event-service", fallbackMethod = "fallbackMethod")
    public boolean existEventById(@PathVariable String id);

    default boolean fallbackMethod(String id, Throwable throwable) {
        return false;
    }
}
