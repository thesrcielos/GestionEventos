package com.eventmanagement.auth.controller;

import com.eventmanagement.auth.dto.TokenResponse;
import com.eventmanagement.auth.dto.UserDTO;
import com.eventmanagement.auth.dto.UserRequest;
import com.eventmanagement.auth.service.KeycloakService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final KeycloakService keycloakService;

    @PostMapping("/create")
    public String createUser(@Nonnull @RequestBody UserDTO userDTO){
        return keycloakService.createUser(userDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestBody UserRequest userRequest){
        return keycloakService.logIn(userRequest);
    }
}
