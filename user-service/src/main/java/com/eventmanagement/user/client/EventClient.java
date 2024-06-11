package com.eventmanagement.user.client;

import com.eventmanagement.user.dto.EventDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface EventClient {

    @GetExchange("http://localhost:8080/api/event/{id}")
    public EventDTO findEventById(@PathVariable String id);
}
