package com.eventmanagement.tickets.client;

import com.eventmanagement.tickets.dto.UserDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserClient {
    @GetExchange("/api/user/{id}")
    public UserDTO getUserById(@PathVariable Long id);

    @GetExchange("/api/user/verify")
    public boolean existUserById(@PathVariable Long id);
}
