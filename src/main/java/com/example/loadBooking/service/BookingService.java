package com.example.loadBooking.service;

import com.example.loadBooking.model.Booking;
import com.example.loadBooking.model.Load;
import com.example.loadBooking.repository.BookingRepository;
import com.example.loadBooking.repository.LoadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LoadRepository loadRepository;

    @Transactional
    public Booking createBooking(Booking booking) {
        Load load = loadRepository.findById(booking.getLoadId())
                .orElseThrow(() -> new RuntimeException("Load not found with ID: " + booking.getLoadId()));

        if (load.getStatus() == Load.Status.CANCELLED) {
            throw new RuntimeException("Cannot book a CANCELLED load.");
        }

        Booking saved = bookingRepository.save(booking);

        load.setStatus(Load.Status.BOOKED);
        loadRepository.save(load);

        return saved;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
    }

    public List<Booking> getBookingsByTransporterId(String transporterId) {
        return bookingRepository.findByTransporterId(transporterId);
    }

    public List<Booking> getBookingsByLoadId(UUID loadId) {
        return bookingRepository.findByLoadId(loadId);
    }

    public Booking updateBooking(UUID id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setTransporterId(updatedBooking.getTransporterId());
            booking.setProposedRate(updatedBooking.getProposedRate());
            booking.setComment(updatedBooking.getComment());
            booking.setStatus(updatedBooking.getStatus());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    @Transactional
    public void deleteBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Load load = loadRepository.findById(booking.getLoadId())
                .orElseThrow(() -> new RuntimeException("Related load not found"));
        load.setStatus(Load.Status.CANCELLED);
        loadRepository.save(load);

        bookingRepository.deleteById(bookingId);
    }

}
