package com.airline.airline_management.model;

//package com.airline.model;

import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
//    private String passengerName;

//    @Email
//    private String email;

    @ManyToOne
    private Flight flight;

    private LocalDateTime bookingDate;

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
}

