package com.example.ticket.service;

import com.example.ticket.dal.ITicketDao;
import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    private final ITicketDao ticketDao;
    @Autowired
    public TicketService(@Qualifier("mysql") ITicketDao ticketDao){
        this.ticketDao = ticketDao;
    }
    public UUID createTicket(Ticket ticket){
        return ticketDao.createTicket(ticket);
    }
    public List<Ticket> getTickets(Optional<UUID> id, Optional<Status> ticketStatus, Optional<Integer> userId) {
        return ticketDao.getTickets(id, ticketStatus, userId);
    }
    public Boolean updateTicket(UUID id, Ticket updatedTicket){
        return ticketDao.updateTicket(id, updatedTicket);
    }
    public Boolean deleteTicket(UUID id){
        return ticketDao.deleteTicket(id);
    }

}
