package com.eventmanagement.apigateway;

import com.eventmanagement.apigateway.dto.UserDTO;
import com.eventmanagement.apigateway.service.KeycloakService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {
    private final KeycloakService keycloakService;
    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public String hello(){
        return "Hello motherfucker";
    }

    @PostMapping("/create")
    public String createUser(@Nonnull @RequestBody UserDTO userDTO){
        return keycloakService.createUser(userDTO);
    }
}
