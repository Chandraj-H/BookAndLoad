package com.example.loadBooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity //annotation explanation in Load class
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID loadId;    //foreign key

    private String transporterId;

    private double proposedRate;

    private String comment;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime requestedAt;

    @PrePersist
    public void prePersist() {
        this.requestedAt = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
