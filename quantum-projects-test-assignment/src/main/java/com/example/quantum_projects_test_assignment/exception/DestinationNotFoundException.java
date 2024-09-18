package com.example.quantum_projects_test_assignment.exception;

import java.io.Serial;

public class DestinationNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public DestinationNotFoundException(String message) {
        super(message);
    }
}
