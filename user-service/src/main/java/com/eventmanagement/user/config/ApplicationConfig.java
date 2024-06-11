package com.eventmanagement.user.config;

import com.eventmanagement.user.dto.EventHistoryResponse;
import com.eventmanagement.user.model.EventHistory;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        Converter<EventHistory, EventHistoryResponse> eventHistoryConverter = context -> {
            EventHistory source = context.getSource();
            EventHistoryResponse destination = new EventHistoryResponse();
            destination.setUserId(source.getUser().getId());
            return destination;
        };

        modelMapper.addConverter(eventHistoryConverter);
        return modelMapper;
    }
}
