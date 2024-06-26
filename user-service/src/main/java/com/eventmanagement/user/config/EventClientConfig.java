package com.eventmanagement.user.config;

import com.eventmanagement.user.client.EventClient;
import com.eventmanagement.user.util.JwtUtil;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class EventClientConfig {

    private final EurekaClient discoveryClient;
    private final JwtUtil jwtUtil;

    private String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("event-service", false);
        return instance.getHomePageUrl();
    }
    @Bean
    public EventClient eventClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(serviceUrl())
                .build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(EventClient.class);
    }



}
