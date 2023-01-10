package com.example.ticket.controller;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import com.example.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/ticket")
@RestController
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket).toString());
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(@RequestParam("id") Optional<UUID> id,
                                                   @RequestParam("status") Optional<Status> ticketStatus,
                                                   @RequestParam("userId") Optional<Integer> userId) {
        if (id.isPresent()) {
            Optional<Ticket> ticket = ticketService.getTicketById(id.get());
            List<Ticket> response = new ArrayList<>();
            if (ticket.isPresent()) response.add(ticket.get());
            return ResponseEntity.ok(response);
        } else if (ticketStatus.isPresent()) {
            return ResponseEntity.ok(ticketService.getTicketsByStatus(ticketStatus.get()));
        } else if (userId.isPresent()) {
            return ResponseEntity.ok(ticketService.getUserTickets(userId.get().intValue()));
        } else {
            return ResponseEntity.ok(ticketService.getAllTickets());
        }

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Boolean> updateTicket(@PathVariable("id") UUID id,
                                                @RequestBody Ticket updatedTicket) {
        return ResponseEntity.ok(ticketService.updateTicket(id, updatedTicket));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> deleteTicket(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(ticketService.deleteTicket(id));
    }


}
