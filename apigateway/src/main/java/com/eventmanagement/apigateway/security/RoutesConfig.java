package com.eventmanagement.apigateway.security;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RoutesConfig {
    private final EurekaClient discoveryClient;

    private String serviceUrl(String name) {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(name, false);
        return instance.getHomePageUrl();
    }
    @Bean
    public RouterFunction<ServerResponse> eventServiceRoute(){
        return route("event-service")
                .route(RequestPredicates.path("/api/event/**"), HandlerFunctions.http(serviceUrl("event-service")))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("eventServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(){
        return route("user-service")
                .route(RequestPredicates.path("/api/user/**"), HandlerFunctions.http(serviceUrl("user-service")))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userHistoryServiceRoute(){
        return route("user-history-service")
                .route(RequestPredicates.path("/api/history/**"), HandlerFunctions.http(serviceUrl("user-service")))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userHistoryServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ticketServiceRoute(){
        return route("ticket-service")
                .route(RequestPredicates.path("/api/ticket/**"), HandlerFunctions.http(serviceUrl("ticket-service")))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("ticketServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute(){
        return route("/fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("service unavailable, please try again"))
                .build();
    }

}
