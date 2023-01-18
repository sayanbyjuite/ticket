package com.example.ticket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    public Ticket(@JsonProperty("title") String title,
                  @JsonProperty("ticketId") Long ticketId,
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

    public Ticket() {
        this.title = "";
        this.subject = "";
        this.status = Status.NOTSET;
    }

    @Column(name = "title")
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId")
    private Long ticketId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "status")
    private Status status;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
