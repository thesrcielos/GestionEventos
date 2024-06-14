package com.eventmanagement.apigateway.config;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

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
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(){
        return route("user-service")
                .route(RequestPredicates.path("/api/user/**"), HandlerFunctions.http(serviceUrl("user-service")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userHistoryServiceRoute(){
        return route("user-history-service")
                .route(RequestPredicates.path("/api/history/**"), HandlerFunctions.http(serviceUrl("user-service")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ticketServiceRoute(){
        return route("ticket-service")
                .route(RequestPredicates.path("/api/ticket/**"), HandlerFunctions.http(serviceUrl("ticket-service")))
                .build();
    }

}
