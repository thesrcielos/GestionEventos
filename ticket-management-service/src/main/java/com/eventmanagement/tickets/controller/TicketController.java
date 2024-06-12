package com.eventmanagement.tickets.controller;

import com.eventmanagement.tickets.dto.TicketRequest;
import com.eventmanagement.tickets.dto.TicketResponse;
import com.eventmanagement.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponse> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponse> getTicketsByUserId(@PathVariable Long id){
        return ticketService.getUserTickets(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse createTicket(@RequestBody TicketRequest ticketRequest){
        return ticketService.saveTicket(ticketRequest);
    }

}
