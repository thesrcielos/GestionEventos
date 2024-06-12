package com.eventmanagement.tickets.config;

import com.eventmanagement.tickets.client.EventClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class EventClientConfig {

    @Value("${event.service.url}")
    private String eventClientUrl;
    @Bean
    public EventClient eventClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(eventClientUrl)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(EventClient.class);
    }
}
