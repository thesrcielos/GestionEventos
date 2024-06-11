package com.eventmanagement.user.controller;

import com.eventmanagement.user.dto.EventHistoryRequest;
import com.eventmanagement.user.dto.EventHistoryResponse;
import com.eventmanagement.user.service.EventHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class EventHistoryController {
    private final EventHistoryService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<EventHistoryResponse> getAllEventHistoryByUserId(@PathVariable Long id){
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventHistoryResponse createEventHistory(@RequestBody EventHistoryRequest request){
        return service.saveEventHistory(request);
    }
}
