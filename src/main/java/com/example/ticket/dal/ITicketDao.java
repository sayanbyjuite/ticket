package com.example.ticket.dal;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITicketDao {
     UUID createTicket(Ticket ticket);
     List<Ticket> getTickets(Optional<UUID> id, Optional<Status> ticketStatus, Optional<Integer> userId);
     Boolean updateTicket(UUID id, Ticket updatedTicket);
     Boolean deleteTicket(UUID id);
}
