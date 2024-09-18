package com.example.quantum_projects_test_assignment.feature.impl;

import com.example.quantum_projects_test_assignment.entity.Destination;
import com.example.quantum_projects_test_assignment.entity.Ticket;
import com.example.quantum_projects_test_assignment.feature.TicketGenerator;
import com.example.quantum_projects_test_assignment.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class TicketGeneratorImpl implements TicketGenerator {
    @Autowired
    DestinationRepository destinationRepository;

    private List<String> generateTicketsIds() {
        return new Random()
                .ints(0, 10000)
                .distinct()
                .limit(100)
                .mapToObj(it -> String.format("%04d", it))
                .toList();
    }

    private List<Double> generateTicketPrices() {
        return new Random()
                .doubles(0.0, 100.0)
                .limit(100)
                .boxed()
                .toList();

    }

    @Override
    public List<Ticket> generateTickets() {
        List<String> ticketsIds = generateTicketsIds();
        List<Destination> destinations = destinationRepository.getDestinations();

        Random generator = new Random();
        DecimalFormat df = new DecimalFormat("0.00");

        List<Ticket> tickets = new ArrayList<>();
        for (String id : ticketsIds) {
            tickets.add(new Ticket(id, destinations.get(generator.nextInt(destinations.size())),
                    Double.valueOf(df.format(generator.nextDouble(1.0, 100.0)).replaceAll(",", "."))));
        }

        return tickets;
    }
}
