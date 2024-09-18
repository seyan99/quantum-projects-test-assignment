package com.example.quantum_projects_test_assignment.repository;

import com.example.quantum_projects_test_assignment.entity.Destination;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DestinationRepository {
    private final List<Destination> destinations = new ArrayList<>();

    @PostConstruct
    private void populateDestinations() {
        destinations.add(new Destination(1, "Ukraine"));
        destinations.add(new Destination(2, "Italy"));
        destinations.add(new Destination(3, "Germany"));
        destinations.add(new Destination(4, "USA"));
        destinations.add(new Destination(5, "England"));
        destinations.add(new Destination(6, "Greece"));
        destinations.add(new Destination(7, "Morocco"));
        destinations.add(new Destination(8, "Spain"));
        destinations.add(new Destination(9, "France"));
    }

    public List<Destination> getDestinations() {
        return new ArrayList<>(destinations) {
        };
    }

    public boolean containsDestination(int destinationId) {
        return destinations.stream().map(Destination::getId).toList().contains(destinationId);
    }
}
