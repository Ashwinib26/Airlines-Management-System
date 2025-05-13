package com.airline.airline_management.model;

//package com.airline.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    // Getters and Setters
}