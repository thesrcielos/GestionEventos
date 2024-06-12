package com.eventmanagement.tickets.client;

import com.eventmanagement.tickets.dto.EventDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface EventClient {

    @GetExchange("/api/event/{id}")
    public EventDTO getEventById(@PathVariable String id);

    @GetExchange("/api/event/verify/{id}")
    public boolean existEventById(@PathVariable String id);
}
