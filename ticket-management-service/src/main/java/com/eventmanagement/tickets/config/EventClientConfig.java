package com.eventmanagement.tickets.config;

import com.eventmanagement.tickets.client.EventClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class EventClientConfig {

    @Value("${event.service.url}")
    private String eventClientUrl;

    private final EurekaClient discoveryClient;

    private String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("event-service", false);
        return instance.getHomePageUrl();
    }
    @Bean
    @LoadBalanced
    public EventClient eventClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(serviceUrl())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(EventClient.class);
    }
}
