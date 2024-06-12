package com.eventmanagement.user.client;

import com.eventmanagement.user.dto.EventDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface EventClient {

    @GetExchange("/api/event/{id}")
    EventDTO findEventById(@PathVariable String id);

    @GetExchange("/api/event/verify/{id}")
    boolean existsEventById(@PathVariable String id);
}
