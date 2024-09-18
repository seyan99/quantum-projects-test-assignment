package com.example.quantum_projects_test_assignment.exception;

import java.io.Serial;

public class BaggageNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3;

    public BaggageNotFoundException(String message) {
        super(message);
    }
}
