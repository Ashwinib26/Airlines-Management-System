package com.airline.airline_management.controller;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getFlights(@RequestParam(required = false) String sort) {
        return flightService.getAllFlights(sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

