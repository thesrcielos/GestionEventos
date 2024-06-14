package com.eventmanagement.tickets.config;

import com.eventmanagement.tickets.client.UserClient;
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
public class UserClientConfig {
    @Value("${user.service.url}")
    private String userServiceUrl;
    private final EurekaClient discoveryClient;

    private String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("USER-SERVICE", false);
        return instance.getHomePageUrl();
    }
    @Bean
    @LoadBalanced
    public UserClient userClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(serviceUrl())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(UserClient.class);
    }
}
