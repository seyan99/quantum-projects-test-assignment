package com.example.quantum_projects_test_assignment.entity;

import java.util.Objects;

public class Ticket {
    private String id;
    private Destination destination;
    private Double price;

    public Ticket(String id, Destination destination, Double price) {
        this.id = id;
        this.destination = destination;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Destination getDestination() {
        return destination;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "id = " + id + ", price = " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(destination, ticket.destination) && Objects.equals(price, ticket.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, price);
    }
}
