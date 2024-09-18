package com.example.quantum_projects_test_assignment.feature.impl;

import com.example.quantum_projects_test_assignment.feature.CouponGenerator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class CouponGeneratorImpl implements CouponGenerator {
    @Override
    public List<Integer> generateCoupons() {
        return IntStream.rangeClosed(1, 100)
                .boxed().toList();
    }
}
