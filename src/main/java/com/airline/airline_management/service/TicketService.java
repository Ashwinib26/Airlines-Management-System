package com.airline.airline_management.service;

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
        return ticketRepo.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public void deleteTicket(Long id) {
        ticketRepo.deleteById(id);
    }
}
