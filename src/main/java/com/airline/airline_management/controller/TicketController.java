package com.airline.airline_management.controller;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> bookTicket(@RequestParam Long flightId, @RequestBody Ticket ticket) {
        Ticket saved = ticketService.createTicket(ticket, flightId);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}

