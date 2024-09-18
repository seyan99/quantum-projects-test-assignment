package com.example.quantum_projects_test_assignment.repository;

import com.example.quantum_projects_test_assignment.feature.CouponGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CouponRepository {
    @Autowired
    private CouponGenerator couponGenerator;

    private List<Integer> coupons = new ArrayList<>();

    @PostConstruct
    private void addCoupons() {
        List<Integer> generatedTickets = couponGenerator.generateCoupons();
        coupons.addAll(generatedTickets);
    }

    public boolean containsCoupon(int couponIndex) {
        return coupons.contains(couponIndex);
    }

    public void removeCoupon(int couponIndex) {
        coupons.remove(couponIndex - 1);
    }
}
