package com.eventmanagement.user.service;

import com.eventmanagement.user.client.EventClient;
import com.eventmanagement.user.dto.EventDTO;
import com.eventmanagement.user.dto.EventHistoryRequest;
import com.eventmanagement.user.dto.EventHistoryResponse;
import com.eventmanagement.user.model.EventHistory;
import com.eventmanagement.user.model.User;
import com.eventmanagement.user.repository.EventHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventHistoryService {
    private final UserService userService;
    private final EventHistoryRepository repository;
    private final EventClient eventClient;
    private final ModelMapper modelMapper;

    public EventHistoryResponse saveEventHistory(EventHistoryRequest request){
        EventDTO eventDTO = eventClient.findEventById(request.getEventId());
        User user = userService.findUser(request.getUserId());
        EventHistory eventHistory = EventHistory.builder()
                .eventId(request.getEventId())
                .user(user)
                .build();
        repository.save(eventHistory);
        return modelMapper.map(eventHistory,EventHistoryResponse.class);
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

