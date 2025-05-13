package com.airline.airline_management.repository;


import com.airline.airline_management.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {}
