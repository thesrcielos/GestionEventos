package com.eventmanagement.event.service;

import com.eventmanagement.event.dto.EventRequest;
import com.eventmanagement.event.dto.EventResponse;
import com.eventmanagement.event.model.Event;
import com.eventmanagement.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    public EventResponse saveEvent(EventRequest eventRequest){
        Event event = modelMapper.map(eventRequest,Event.class);
        eventRepository.save(event);
        return modelMapper.map(event,EventResponse.class);
    }

    public EventResponse updateEvent(EventRequest eventRequest){
        String eventId = eventRequest.getId();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("error event with id " + eventId+ "was not found"));
        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setBeginningTime(eventRequest.getBeginningTime());
        event.setFinishingTime(eventRequest.getFinishingTime());
        event.setPrice(eventRequest.getPrice());
        eventRepository.save(event);
        return modelMapper.map(event,EventResponse.class);
    }

    public String deleteEvent(String eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("error event with id " + eventId+ "was not found"));
        eventRepository.deleteById(eventId);
        return "event with id " + eventId + " was successfully deleted";
    }

    public EventResponse getEventById(String eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("error event with id " + eventId+ "was not found"));
        return modelMapper.map(event,EventResponse.class);
    }

    public List<EventResponse> getAllEvents(){
        return eventRepository.findAll().stream().map(event->modelMapper.map(event,EventResponse.class)).toList();
    }

}
