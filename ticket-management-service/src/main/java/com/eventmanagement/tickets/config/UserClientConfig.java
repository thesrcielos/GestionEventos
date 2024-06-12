package com.eventmanagement.tickets.config;

import com.eventmanagement.tickets.client.UserClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class UserClientConfig {
    @Value("${user.service.url}")
    private String userServiceUrl;
    @Bean
    public UserClient userClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(userServiceUrl)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(UserClient.class);
    }
}
