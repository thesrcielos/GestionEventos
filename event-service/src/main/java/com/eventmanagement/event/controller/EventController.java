package com.eventmanagement.event.controller;

import com.eventmanagement.event.dto.EventRequest;
import com.eventmanagement.event.dto.EventResponse;
import com.eventmanagement.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse getEventById(@PathVariable String id){
        return eventService.getEventById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(@RequestBody EventRequest request){
        return eventService.saveEvent(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EventResponse updateEvent(@RequestBody EventRequest eventRequest){
        return eventService.updateEvent(eventRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEvent(@PathVariable String id){
        return eventService.deleteEvent(id);
    }

    @GetMapping("/verify/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean existEventById(@PathVariable String id){
        return eventService.existEventById(id);
    }
}
