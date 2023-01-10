package com.example.ticket.dal;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.*;

@Repository("mysql")
public class TicketDaoMysqlImpl implements  ITicketDao{

    List<Ticket> DB = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketDaoMysqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID createTicket(Ticket ticket) {
        String update = "INSERT into ticket (title, id, subject, status, createdBy) VALUES (?, ?, ?, ?, ?)";
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(update, new Object[]{
                ticket.getTitle(),
                id.toString(),
                ticket.getSubject(),
                Status.OPEN.toString(),
                ticket.getCreatedBy()});
        return id;
    }

    @Override
    public List<Ticket> getAllTickets() {
        String query = "Select * from ticket";
        return jdbcTemplate.query(query,(resultSet, i) -> {
            return new Ticket(
                    resultSet.getString("title"),
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("subject"),
                    Status.valueOf(resultSet.getString("status")),
                    resultSet.getInt("createdBy"),
                    resultSet.getTimestamp("createdAt"),
                    resultSet.getTimestamp("updatedAt")
            );
        });
    }

    @Override
    public List<Ticket> getTicketsByStatus(Status ticketStatus) {
        String query = "Select * from ticket where status = ?";
        return jdbcTemplate.query(query, new Object[]{ticketStatus.toString()},(resultSet, i) -> {
            return new Ticket(
                    resultSet.getString("title"),
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("subject"),
                    Status.valueOf(resultSet.getString("status")),
                    resultSet.getInt("createdBy"),
                    resultSet.getTimestamp("createdAt"),
                    resultSet.getTimestamp("updatedAt")
            );
        });
    }

    @Override
    public Optional<Ticket> getTicketById(UUID id) {
        String query = "Select * from ticket where id = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        query,
                        new Object[]{id.toString()},
                        Ticket.class)
        );
    }

    @Override
    public List<Ticket> getUserTickets(int id) {
        String query = "Select * from ticket where status = ?";
        return jdbcTemplate.query(query, new Object[]{id},(resultSet, i) -> {
            return new Ticket(
                    resultSet.getString("title"),
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("subject"),
                    Status.valueOf(resultSet.getString("status")),
                    resultSet.getInt("createdBy"),
                    resultSet.getTimestamp("createdAt"),
                    resultSet.getTimestamp("updatedAt")
            );
        });
    }

    @Override
    public Boolean updateTicket(UUID id, Ticket updatedTicket) {
//        String update = "Update ticket where title, id, subject, status, createdBy) VALUES (?, ?, ?, ?, ?)";
//        UUID id = UUID.randomUUID();
//        jdbcTemplate.update(update, new Object[]{
//                updatedTicket.getTitle(),
//                id.toString(),
//                updatedTicket.getSubject(),
//                Status.OPEN.toString(),
//                updatedTicket.getCreatedBy()
//        });
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteTicket(UUID id) {

        return Boolean.TRUE;
    }
}
