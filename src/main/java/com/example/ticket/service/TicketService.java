package com.example.ticket.service;

import com.example.ticket.dal.ITicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final ITicketDao ticketDao;
    @Autowired
    public TicketService(@Qualifier("mysql") ITicketDao ticketDao){
        this.ticketDao = ticketDao;
    }
    public createTicket()

}
