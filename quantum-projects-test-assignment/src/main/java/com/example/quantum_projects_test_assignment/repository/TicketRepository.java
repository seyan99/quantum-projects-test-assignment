package com.example.quantum_projects_test_assignment.repository;

import com.example.quantum_projects_test_assignment.entity.Ticket;
import com.example.quantum_projects_test_assignment.feature.TicketGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketRepository {
    @Autowired
    private TicketGenerator ticketGenerator;

    private final List<Ticket> tickets = new ArrayList<>();

    @PostConstruct
    private void addTickets() {
        List<Ticket> generatedTickets = ticketGenerator.generateTickets();
        tickets.addAll(generatedTickets);
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public boolean containsTicket(String ticketId) {
        return tickets.stream().map(Ticket::getId).toList().contains(ticketId);
    }
}
