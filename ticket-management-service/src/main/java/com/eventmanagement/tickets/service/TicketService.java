package com.eventmanagement.tickets.service;

import com.eventmanagement.tickets.client.EventClient;
import com.eventmanagement.tickets.client.UserClient;
import com.eventmanagement.tickets.dto.TicketRequest;
import com.eventmanagement.tickets.dto.TicketResponse;
import com.eventmanagement.tickets.model.Ticket;
import com.eventmanagement.tickets.repositry.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final EventClient eventClient;
    private final UserClient userClient;
    private final ModelMapper modelMapper;

    public TicketResponse saveTicket(TicketRequest ticketRequest){
        validateUserId(ticketRequest.getUserId());
        validateEventId(ticketRequest.getEventId());
        Ticket ticket = Ticket.builder()
                .userId(ticketRequest.getUserId())
                .eventId(ticketRequest.getEventId())
                .build();
        ticket.setEmissionDate(LocalDateTime.now());
        ticketRepository.save(ticket);
        return modelMapper.map(ticket,TicketResponse.class);
    }
    public List<TicketResponse> getAllTickets(){
        return ticketRepository.findAll().stream()
                .map(ticket -> modelMapper.map(ticket,TicketResponse.class))
                .toList();
    }
    public List<TicketResponse> getUserTickets(Long id){
        return ticketRepository.findByUserId(id).stream()
                .map(ticket->modelMapper.map(ticket,TicketResponse.class))
                .toList();
    }
    private void validateUserId(Long userId){
        boolean userExists = userClient.existUserById(userId);
        if(!userExists) throw new RuntimeException("User with id "+ userId +" not found");
    }

    private void validateEventId(String eventId){
        boolean eventExists = eventClient.existEventById(eventId);
        if(!eventExists) throw new RuntimeException("event with id "+ eventId +" not found");
    }



}
