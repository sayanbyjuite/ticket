package com.example.ticket.controller;

import com.example.ticket.TicketApplication;
import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import com.example.ticket.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/ticket")
@RestController
public class TicketController {
    private final TicketService ticketService;

    private static final Logger logger = LogManager.getLogger(TicketController.class);

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody Ticket ticket) {

        return ResponseEntity.ok(ticketService.createTicket(ticket).toString());
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(@RequestParam("id") Optional<Long> id,
                                                   @RequestParam("status") Optional<Status> ticketStatus,
                                                   @RequestParam("userId") Optional<Integer> userId) {
        return ResponseEntity.ok(ticketService.getTickets(id, ticketStatus, userId));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Boolean> updateTicket(@PathVariable("id") Long id,
                                                @RequestBody Ticket updatedTicket) {
        return ResponseEntity.ok(ticketService.updateTicket(id, updatedTicket));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> deleteTicket(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ticketService.deleteTicket(id));
    }


}
