package com.example.quantum_projects_test_assignment.controller;

import com.example.quantum_projects_test_assignment.entity.Destination;
import com.example.quantum_projects_test_assignment.entity.Ticket;
import com.example.quantum_projects_test_assignment.exception.CouponNotFoundException;
import com.example.quantum_projects_test_assignment.exception.DestinationNotFoundException;
import com.example.quantum_projects_test_assignment.service.impl.AppServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class AppController {
    @Autowired
    private AppServiceImpl appServiceImpl;

    private Scanner scanner;

    @PostConstruct
    private void init() {
        scanner = new Scanner(System.in);
    }

    public void runApp() {
        appServiceImpl.getHelp();

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        var ticketIdPattern = Pattern.compile("\\d{4}");
        var ticketAndDestinationdIdPattern = Pattern.compile("[t] [1-9]*");

        boolean status = false;
        while (!status) {
            String input = scanner.nextLine();
            if (input.equals("help")) {
                appServiceImpl.getHelp();
            }
            if (input.equals("d")) {
                List<Destination> destinations = appServiceImpl.getDestinations();
                destinations.forEach(System.out::println);
            }
            if (input.equals("rb")) {
                int registeredBaggageId = appServiceImpl.registerBaggage();
                System.out.println("your baggage id: " + registeredBaggageId);
            }
            if (input.equals("ci")) {
                System.out.println(appServiceImpl.checkIn(scanner));
            }
            if (input.equals("cpn")) {
                try {
                    appServiceImpl.useCoupon(scanner);
                } catch (CouponNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("ticket price format is not valid, please try again");
                }
            }
            if (ticketIdPattern.matcher(input).matches()) {
                System.out.println(appServiceImpl.isTicketAvailable(input));
            }
            if (ticketAndDestinationdIdPattern.matcher(input).matches()) {
                try {
                    String[] ticketAndDestinationInput = input.split(" ");
                    String destinationId = ticketAndDestinationInput[1];
                    List<Ticket> destinationTickets = appServiceImpl.getDestinationTickets(destinationId);
                    System.out.printf("list of tickets for destination with id %s%n:", destinationId);
                    destinationTickets.forEach(System.out::println);
                } catch (DestinationNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (input.equals("close")) {
                status = true;
            }
        }
        scanner.close();
        System.exit(0);
    }
}
