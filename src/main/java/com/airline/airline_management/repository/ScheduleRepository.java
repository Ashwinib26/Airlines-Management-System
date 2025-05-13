package com.airline.airline_management.repository;


import com.airline.airline_management.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByFlightIdAndDepartureTimeBetween(Long flightId, LocalDateTime start, LocalDateTime end);
}