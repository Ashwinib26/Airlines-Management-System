package com.airline.airline_management.model;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private String fromLocation;
    private String toLocation;

    // One-to-Many with Schedule
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();
}
