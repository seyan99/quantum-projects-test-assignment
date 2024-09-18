package com.example.quantum_projects_test_assignment.service;

import com.example.quantum_projects_test_assignment.entity.Destination;
import com.example.quantum_projects_test_assignment.entity.Ticket;

import java.util.List;
import java.util.Scanner;

public interface AppService {
    void getHelp();

    List<Destination> getDestinations();

    List<Ticket> getDestinationTickets(String destinationId);

    boolean isTicketAvailable(String ticketId);

    int registerBaggage();

    boolean checkIn(Scanner scanner);

    void useCoupon(Scanner scanner);
}
