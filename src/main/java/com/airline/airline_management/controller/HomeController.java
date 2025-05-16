package com.airline.airline_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<h1>Welcome to the Airline Management System!</h1>" +
                "<p>Your journey begins here. Please choose an option below:</p>" +
                "<ul>" +
                "<li><a href=\"#\">Book a Ticket</a></li>" +
                "<li><a href=\"#\">Cancel a Ticket</a></li>" +
                "<li><a href=\"#\">Check Available Flights</a></li>" +
                "<li><a href=\"#\">Flight Status</a></li>" +
                "<li><a href=\"#\">Customer Support</a></li>" +
                "</ul>" +
                "<p>More features coming soon. Stay tuned!</p>";
    }
}
