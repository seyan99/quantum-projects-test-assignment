package com.example.quantum_projects_test_assignment.service.impl;

import com.example.quantum_projects_test_assignment.entity.Destination;
import com.example.quantum_projects_test_assignment.entity.Ticket;
import com.example.quantum_projects_test_assignment.exception.DestinationNotFoundException;
import com.example.quantum_projects_test_assignment.repository.BaggageRepository;
import com.example.quantum_projects_test_assignment.repository.CouponRepository;
import com.example.quantum_projects_test_assignment.repository.DestinationRepository;
import com.example.quantum_projects_test_assignment.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppServiceImplTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private DestinationRepository destinationRepository;
    @Mock
    private BaggageRepository baggageRepository;
    @Mock
    private CouponRepository couponRepository;
    @InjectMocks
    private AppServiceImpl appService;

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

    @Test
    void getDestinations() {
        when(destinationRepository.getDestinations()).thenReturn(getDestinationsList());

        List<Destination> destinations = appService.getDestinations();

        Assertions.assertEquals(destinations, getDestinationsList());
    }

    @Test
    void getDestinationTickets() {
        when(destinationRepository.containsDestination(1)).thenReturn(true);
        when(ticketRepository.getAllTickets()).thenReturn(getTicketsList());

        List<Ticket> tickets = appService.getDestinationTickets("1");

        Assertions.assertEquals(tickets, getTicketsList());
    }

    @Test
    void getDestinationTickets_ThrowDestinationNotFoundException() {
        Assertions.assertThrows(DestinationNotFoundException.class, () -> {
            appService.getDestinationTickets("1");
        });
    }

    @Test
    void isTicketAvailable() {
        String ticketId = "1";

        when(ticketRepository.containsTicket(ticketId)).thenReturn(true);

        boolean ticketAvailable = appService.isTicketAvailable(ticketId);

        Assertions.assertTrue(ticketAvailable);
    }

    @Test
    void registerBaggage() {
    }

    @Test
    void checkIn() {
    }

    @Test
    void useCoupon() {
    }

    private List<Destination> getDestinationsList() {
        return Arrays.asList(
                new Destination(1, "Ukraine"),
                new Destination(1, "Morocco"),
                new Destination(1, "Spain")
        );
    }

    private List<Ticket> getTicketsList() {
        Destination spain = new Destination(1, "Spain");
        return Arrays.asList(
                new Ticket("0001", spain, 5.00),
                new Ticket("0002", spain, 10.00),
                new Ticket("0003", spain, 15.00)
        );
    }
}