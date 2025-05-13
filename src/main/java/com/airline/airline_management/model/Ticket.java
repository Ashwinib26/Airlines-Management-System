package com.airline.airline_management.model;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private String email;

    @ManyToOne
    private Flight flight;

    private LocalDateTime bookingDate;
}
