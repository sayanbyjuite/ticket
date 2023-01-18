package com.example.ticket.repository;

import com.example.ticket.model.Status;
import com.example.ticket.model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryCustomImpl implements TicketRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ticket> getTicketByStatusAndTicketIdAndUserId(Optional<Long> id, Optional<Status> status, Optional<Integer> createdBy) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = cb.createQuery(Ticket.class);
        Root<Ticket> ticket = query.from(Ticket.class);
        List<Predicate> predicates = new ArrayList<>();
        if (id.isPresent()) {
            Path<String> ticketIdPath = ticket.get("ticketId");
            predicates.add(cb.equal(ticketIdPath, id.get()));
        }
        if (status.isPresent()) {
            Path<String> statusPath = ticket.get("status");
            predicates.add(cb.equal(statusPath, status.get()));
        }
        if (createdBy.isPresent()) {
            Path<String> createdByPath = ticket.get("createdBy");
            predicates.add(cb.equal(createdByPath, createdBy.get().intValue()));
        }

        query.select(ticket)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
