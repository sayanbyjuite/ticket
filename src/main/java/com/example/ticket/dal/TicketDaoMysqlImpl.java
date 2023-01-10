package com.example.ticket.dal;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mysql")
public class TicketDaoMysqlImpl implements  ITicketDao{

    List<Ticket> DB = new ArrayList<>();

    @Override
    public Boolean createTicket(Ticket ticket) {
        DB.add(ticket);
        return Boolean.TRUE;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return DB;
    }

    @Override
    public List<Ticket> getTicketsByStatus(Status ticketStatus) {
        return DB;
    }

    @Override
    public Optional<Ticket> getTicketById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Ticket> getUserTickets(int id) {
        return DB;
    }

    @Override
    public Boolean updateTicket(UUID id, Ticket updatedTicket) {
        return null;
    }

    @Override
    public Boolean deleteTicket(UUID id) {
        return null;
    }
}
