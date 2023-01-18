package com.example.ticket.repository;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepositoryCustom {
    List<Ticket> getTicketByStatusAndTicketIdAndUserId(Optional<Long> id, Optional<Status> status, Optional<Integer> createdBy);
}
