package com.airline.airline_management.service;

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

