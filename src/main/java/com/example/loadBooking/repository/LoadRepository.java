package com.example.loadBooking.repository;

import com.example.loadBooking.model.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LoadRepository extends JpaRepository<Load, UUID> {

    List<Load> findByShipperId(String shipperId);

    List<Load> findByTruckType(String truckType);

}
