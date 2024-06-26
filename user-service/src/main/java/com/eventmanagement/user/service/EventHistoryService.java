package com.eventmanagement.user.service;

import com.eventmanagement.user.client.EventClient;
import com.eventmanagement.user.dto.EventDTO;
import com.eventmanagement.user.dto.EventHistoryRequest;
import com.eventmanagement.user.dto.EventHistoryResponse;
import com.eventmanagement.user.model.EventHistory;
import com.eventmanagement.user.model.User;
import com.eventmanagement.user.repository.EventHistoryRepository;
import com.eventmanagement.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventHistoryService {
    private final UserService userService;
    private final EventHistoryRepository repository;
    private final EventClient eventClient;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    public EventHistoryResponse saveEventHistory(EventHistoryRequest request){
        verifyEventId(request.getEventId());
        User user = userService.findUser(request.getUserId());
        EventHistory eventHistory = EventHistory.builder()
                .eventId(request.getEventId())
                .user(user)
                .build();
        repository.save(eventHistory);
        return modelMapper.map(eventHistory,EventHistoryResponse.class);
    }

    private void verifyEventId(String eventId){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.AUTHORIZATION,"Bearer "+jwtUtil.getTokenInfo());
        boolean existEvent = eventClient.existsEventById(eventId,headers);
       if(!existEvent) throw new RuntimeException("Event with id " + eventId +" not found");
    }
    public List<EventHistoryResponse> getAllEventsByUserId(Long userId){
        return repository.findByUserId(userId).stream()
                .map(event->modelMapper.map(event,EventHistoryResponse.class))
                .toList();
    }

    public void deleteHistoryResponse(Long id){
        repository.deleteById(id);
    }

    public List<EventHistoryResponse> getAllEventHistory(){
        return repository.findAll().stream()
            .map(event->modelMapper.map(event,EventHistoryResponse.class))
                .toList();
    }


}

