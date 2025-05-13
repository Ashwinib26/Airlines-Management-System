package com.airline.airline_management.repository;

import com.airline.airline_management.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {}