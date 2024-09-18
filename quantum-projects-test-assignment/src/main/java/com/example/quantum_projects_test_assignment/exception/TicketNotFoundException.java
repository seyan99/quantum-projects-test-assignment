package com.example.quantum_projects_test_assignment.exception;

import java.io.Serial;

public class TicketNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2;

    public TicketNotFoundException(String message) {
        super(message);
    }
}
