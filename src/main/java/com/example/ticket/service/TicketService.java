package com.example.ticket.service;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import com.example.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Long createTicket(Ticket ticket){
        ticket.setStatus(Status.OPEN);
        Ticket createdTicket = ticketRepository.save(ticket);
        return createdTicket.getTicketId();
    }
    public List<Ticket> getTickets(Optional<Long> id, Optional<Status> ticketStatus, Optional<Integer> userId) {
        return ticketRepository.getTicketByStatusAndTicketIdAndUserId(id, ticketStatus, userId);
    }
    public Boolean updateTicket(Long id, Ticket updatedTicket){
        Optional<Ticket> fetchedTicket = ticketRepository.findById(id);
        if( fetchedTicket.isEmpty() )
            return false;
        Ticket ticket = fetchedTicket.get();
        if( updatedTicket.getStatus() != Status.NOTSET ) {
            ticket.setStatus(updatedTicket.getStatus());
        }
        if( !updatedTicket.getSubject().isEmpty()) {
            ticket.setSubject(updatedTicket.getSubject());
        }
        if( !updatedTicket.getTitle().isEmpty()) {
            ticket.setTitle(updatedTicket.getTitle());
        }
        ticketRepository.save(ticket);
        return true;
    }
    public Boolean deleteTicket(Long id){
        Optional<Ticket> fetchedTicket = ticketRepository.findById(id);
        if( fetchedTicket.isEmpty() )
            return false;
        ticketRepository.deleteById(id);
        return null;
    }

}
