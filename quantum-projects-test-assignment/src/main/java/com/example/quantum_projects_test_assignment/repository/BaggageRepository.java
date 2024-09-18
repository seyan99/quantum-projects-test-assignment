package com.example.quantum_projects_test_assignment.repository;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BaggageRepository {
    private Set<Integer> baggages = new HashSet<>();

    public int addBaggage(int baggageId) {
        baggages.add(baggageId);
        return baggageId;
    }

    public boolean containsBaggage(int baggageId) {
        return baggages.contains(baggageId);
    }
}
