package com.airline.airline_management.service;

import com.airline.airline_management.model.Flight;
import com.airline.airline_management.model.Ticket;
import com.airline.airline_management.repository.FlightRepository;
import com.airline.airline_management.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private FlightRepository flightRepo;

    public Ticket createTicket(Ticket ticket, Long flightId) {
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        ticket.setFlight(flight);
        ticket.setBookingDate(LocalDateTime.now());

        return ticketRepo.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public void deleteTicket(Long id) {
        ticketRepo.deleteById(id);
    }
}
