package com.airline.airline_management.model;

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
}
