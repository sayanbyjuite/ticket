package com.example.ticket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
public class Ticket {
    public Ticket(@JsonProperty("title") String title,
                  @JsonProperty("ticketId") UUID ticketId,
                  @JsonProperty("subject") String subject,
                  @JsonProperty("status") Status status,
                  @JsonProperty("createdBy") int createdBy,
                  @JsonProperty("createdAt") Timestamp createdAt,
                  @JsonProperty("updatedAt") Timestamp updatedAt) {
        this.title = title;
        this.ticketId = ticketId;
        this.subject = subject;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private final String title;
    private final UUID ticketId;
    private final String subject;
    private final Status status;
    private final int createdBy;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
}
