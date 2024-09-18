package com.example.quantum_projects_test_assignment.service.impl;

import com.example.quantum_projects_test_assignment.entity.Destination;
import com.example.quantum_projects_test_assignment.entity.Ticket;
import com.example.quantum_projects_test_assignment.exception.CouponNotFoundException;
import com.example.quantum_projects_test_assignment.exception.DestinationNotFoundException;
import com.example.quantum_projects_test_assignment.repository.BaggageRepository;
import com.example.quantum_projects_test_assignment.repository.CouponRepository;
import com.example.quantum_projects_test_assignment.repository.DestinationRepository;
import com.example.quantum_projects_test_assignment.repository.TicketRepository;
import com.example.quantum_projects_test_assignment.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;

@Component
public class AppServiceImpl implements AppService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private BaggageRepository baggageRepository;
    @Autowired
    private CouponRepository couponRepository;

    private final List<Double> discounts = Arrays.asList(0.1, 0.5, 0.6);

    @Override
    public void getHelp() {
        System.out.println("""
                =========================================================================
                "help" - get list of commands
                "d" - list of available destinations
                "t + destinationId" - list of available tickets for destination
                "ticketId" - check if ticket is available, id should be 4-digit number
                "rb" - register your baggage
                "ci" - check-in your baggage
                "cpn" - use promo coupon
                "close" - stop application
                =========================================================================
                """);
    }

    @Override
    public List<Destination> getDestinations() {
        return destinationRepository.getDestinations();
    }

    @Override
    public List<Ticket> getDestinationTickets(String destinationId) {
        int parsedId = Integer.parseInt(destinationId);

        if (!destinationRepository.containsDestination(parsedId)) {
            throw new DestinationNotFoundException(String.format("destination with provided id %s is not found \n" +
                    "type \"d\" to see list of available destinations and their ids", destinationId));
        }

        return ticketRepository.getAllTickets().stream()
                .filter(it -> it.getDestination().getId() == parsedId)
                .toList();


    }

    @Override
    public boolean isTicketAvailable(String ticketId) {
        return ticketRepository.containsTicket(ticketId);
    }

    @Override
    public int registerBaggage() {
        Random random = new Random();
        int baggageId = random.nextInt(10000, 100000);
        return baggageRepository.addBaggage(baggageId);
    }

    @Override
    public boolean checkIn(Scanner scanner) {
        System.out.println("please enter your destination id:");
        int destinationId = scanner.nextInt();
        System.out.println("please enter your baggage id:");
        int baggageId = scanner.nextInt();
        return destinationRepository.containsDestination(destinationId) && baggageRepository.containsBaggage(baggageId);
    }

    @Override
    public void useCoupon(Scanner scanner) throws InputMismatchException{
        System.out.println("please enter your coupon index:");
        int couponIndex = scanner.nextInt();

        if (!couponRepository.containsCoupon(couponIndex)) {
            throw new CouponNotFoundException("No coupon found with the provided index: " + couponIndex);
        }
        couponRepository.removeCoupon(couponIndex);

        System.out.println("please enter your ticket price using such format: 100.00 or 100");
        double ticketPrice  = scanner.nextDouble();;

        Random random = new Random();
        Double discount = discounts.get(random.nextInt(discounts.size()));
        Integer discountPercents = (int)(discount * 100);
        Double newPrice = ticketPrice - ticketPrice * discount;

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("your ticket price with discount of " + discountPercents + "% = "
                + Double.valueOf(df.format(newPrice).replaceAll(",", ".")));
    }
}
