package com.eventmanagement.apigateway.config;

import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class RoutesConfig {
    @Bean
    public RouterFunction<ServerResponse> eventServiceRoute(){
        return route("event-service")
                .route(RequestPredicates.path("/api/event/**"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(){
        return route("user-service")
                .route(RequestPredicates.path("/api/user/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userHistoryServiceRoute(){
        return route("user-history-service")
                .route(RequestPredicates.path("/api/history/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ticketServiceRoute(){
        return route("ticket-service")
                .route(RequestPredicates.path("/api/ticket/**"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }
}
