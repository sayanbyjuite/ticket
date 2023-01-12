package com.example.ticket.dal;

import com.example.ticket.controller.TicketController;
import com.example.ticket.exception.TicketIdNotFoundException;
import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.*;

@Repository("mysql")
public class TicketDaoMysqlImpl implements ITicketDao {
    private static final Logger logger = LogManager.getLogger(TicketController.class);
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
        logger.info("Creating ticket with id: " + id);
        jdbcTemplate.update(update, new Object[]{
                ticket.getTitle(),
                id.toString(),
                ticket.getSubject(),
                Status.OPEN.toString(),
                ticket.getCreatedBy()});
        logger.info("Successfully created ticket with id: " + id);
        return id;
    }

    @Override
    public List<Ticket> getTickets(Optional<UUID> id, Optional<Status> ticketStatus, Optional<Integer> userId) {
        String query = "Select * from ticket";
        String filter = " where ";
        int filtersCount = 0;
        List<Object> filterValues = new ArrayList<Object>();
        if (id.isPresent()) {
            filter += "id = ?";
            filtersCount++;
            filterValues.add(id.get().toString());
        }
        if (ticketStatus.isPresent()) {
            filter += (filtersCount > 0 ? " and " : "") + "status = ?";
            filtersCount++;
            filterValues.add(ticketStatus.get().toString());
        }
        if (userId.isPresent()) {
            filter += (filtersCount > 0 ? " and " : "") + "createdBy = ?";
            filtersCount++;
            filterValues.add(userId.get());
        }
        query += filtersCount > 0 ? filter : "";
        return jdbcTemplate.query(query, filterValues.toArray(), (resultSet, i) -> {
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
        String query = "SELECT COUNT(*) FROM TICKET WHERE id = ?";
        logger.info("Checking if ticket id : " + id + " exists.");
        int count = jdbcTemplate.queryForObject(query, new Object[]{id.toString()}, Integer.class);
        if (count == 0) {
            logger.error("Ticket id: " + id + " does not exist.");
            throw  new TicketIdNotFoundException("Ticket Id not found :(");
        }
        logger.info("Ticket id: " + id + " exists.");
        String update = "UPDATE ticket SET title = ?, subject = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(update, new Object[]{
                updatedTicket.getTitle(),
                updatedTicket.getSubject(),
                updatedTicket.getStatus().toString(),
                id.toString()
        });
        logger.info("Successfully Updated ticket id: " + id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteTicket(UUID id) {
        String query = "SELECT COUNT(*) FROM TICKET WHERE id = ?";
        logger.info("Checking if ticket id : " + id + " exists.");
        int count = jdbcTemplate.queryForObject(query, new Object[]{id.toString()}, Integer.class);
        if (count == 0) {
            logger.error("Ticket id: " + id + " does not exist.");
            throw new TicketIdNotFoundException("Ticket Id not found :(");
        }
        logger.info("Ticket id: " + id + " exists.");
        String delete = "DELETE FROM ticket WHERE id = ?";
        jdbcTemplate.update(delete, new Object[]{id.toString()});
        logger.info("Successfully deleted ticket id: " + id);
        return Boolean.TRUE;
    }
}
