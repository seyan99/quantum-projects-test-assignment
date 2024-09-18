package com.example.quantum_projects_test_assignment.exception;

import java.io.Serial;

public class CouponNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4;

    public CouponNotFoundException(String message) {
        super(message);
    }
}
