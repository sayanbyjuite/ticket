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

    public List<Ticket> getAllTickets(){
        return ticketDao.getAllTickets();
    }

    public Optional<Ticket> getTicketById(UUID id){
        return ticketDao.getTicketById(id);
    }

    public List<Ticket> getTicketsByStatus(Status ticketStatus){
        return ticketDao.getTicketsByStatus(ticketStatus);
    }
    public List<Ticket> getUserTickets(int id){
        return ticketDao.getUserTickets(id);
    }
    public Boolean updateTicket(UUID id, Ticket updatedTicket){
        return ticketDao.updateTicket(id, updatedTicket);
    }
    public Boolean deleteTicket(UUID id){
        return ticketDao.deleteTicket(id);
    }

}
