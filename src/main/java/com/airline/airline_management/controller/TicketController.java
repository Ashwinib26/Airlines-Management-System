package com.airline.airline_management.controller;

import com.airline.airline_management.model.Ticket;
import com.airline.airline_management.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book/{flightId}")
    public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket, @PathVariable Long flightId) {
        Ticket createdTicket = ticketService.createTicket(ticket, flightId);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    // ✅ GET ticket by ID
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    // ✅ DELETE ticket by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }


}
