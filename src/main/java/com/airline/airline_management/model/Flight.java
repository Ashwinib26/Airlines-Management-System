package com.airline.airline_management.model;

//package com.airline.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String airline;
    private String fromLocation;
    private String toLocation;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    // Getters and Setters
}

