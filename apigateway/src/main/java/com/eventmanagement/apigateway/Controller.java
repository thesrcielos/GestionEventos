package com.eventmanagement.apigateway;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public String hello(){
        return "Hello motherfucker";
    }
}
