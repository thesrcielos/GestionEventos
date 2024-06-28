package com.eventmanagement.tickets.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

public interface EventClient {
    @GetExchange("/api/event/verify/{id}")
    @CircuitBreaker(name = "event-service", fallbackMethod = "fallbackMethod")
    public boolean existEventById(@PathVariable String id, @RequestHeader Map<String,String> authorization);

    default boolean fallbackMethod(String id,Map<String,String> authorization, Throwable throwable) {
        return false;
    }
}
