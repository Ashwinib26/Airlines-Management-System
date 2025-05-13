package com.airline.airline_management.service;

import com.airline.airline_management.model.Flight;
import com.airline.airline_management.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepo;

    public List<Flight> getAllFlights(String sort) {
        if ("asc".equalsIgnoreCase(sort)) {
            return flightRepo.findAll(Sort.by("airline").ascending());
        }
        return flightRepo.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepo.findById(id);
    }
}


