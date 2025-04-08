package com.example.loadBooking.repository;

import com.example.loadBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByTransporterId(String transporterId);

    List<Booking> findByLoadId(UUID loadId);
}

