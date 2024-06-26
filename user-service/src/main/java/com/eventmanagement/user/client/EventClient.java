package com.eventmanagement.user.client;

import com.eventmanagement.user.dto.EventDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

public interface EventClient {

    @GetExchange("/api/event/{id}")
    EventDTO findEventById(@PathVariable String id);

    @GetExchange("/api/event/verify/{id}")
    @CircuitBreaker(name = "event-service" , fallbackMethod = "fallbackMethod")
    boolean existsEventById(@PathVariable String id, @RequestHeader MultiValueMap<String, String> authorization);

    default boolean fallbackMethod(String id,MultiValueMap<String, String> authorization, Throwable throwable) {
        System.out.println("Error"+throwable.getMessage());
        return false;
    }



}
