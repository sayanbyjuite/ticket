package com.example.ticket.dal;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITicketDao {
     Boolean createTicket(Ticket ticket);
     List<Ticket> getAllTickets();
     Optional<Ticket> getTicketById(UUID id);
     List<Ticket> getTicketsByStatus(Status ticketStatus);
     List<Ticket> getUserTickets(int id);
     Boolean updateTicket(UUID id, Ticket updatedTicket);
     Boolean deleteTicket(UUID id);
}
