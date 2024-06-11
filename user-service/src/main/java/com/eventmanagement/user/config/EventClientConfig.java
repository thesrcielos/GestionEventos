package com.eventmanagement.user.config;

import com.eventmanagement.user.client.EventClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class EventClientConfig {
    @Value("${event.client.url}")
    private String eventClientUrl;

    @Bean
    public EventClient eventClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(eventClientUrl)
                .build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(EventClient.class);
    }

}
