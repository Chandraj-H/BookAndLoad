package com.example.loadBooking.controller;

import com.example.loadBooking.model.Booking;
import com.example.loadBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public List<Booking> getBookings(@RequestParam(required = false) String transporterId,
                                     @RequestParam(required = false) UUID loadId) {
        if (transporterId != null) {
            return bookingService.getBookingsByTransporterId(transporterId);
        } else if (loadId != null) {
            return bookingService.getBookingsByLoadId(loadId);
        } else {
            return bookingService.getAllBookings();
        }
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable UUID id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable UUID id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
    }

}
