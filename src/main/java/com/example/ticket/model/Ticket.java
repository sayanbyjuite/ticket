package com.example.ticket.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Ticket {
    public Ticket(String title, UUID id, String subject, Status status, int createdBy, Date createdAt, Date updatedAt) {
        this.title = title;
        this.id = id;
        this.subject = subject;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private final String title;
    private final UUID id;
    private final String subject;
    private final Status status;
    private final int createdBy;
    private final Date createdAt;
    private final Date updatedAt;
}
