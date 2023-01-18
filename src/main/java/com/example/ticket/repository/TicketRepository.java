package com.example.ticket.repository;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketRepositoryCustom {
}
