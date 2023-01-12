package com.example.ticket.exception;

public class TicketIdNotFoundException extends RuntimeException {
    public TicketIdNotFoundException(String message) {
        super(message);
    }

    public TicketIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
