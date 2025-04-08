package com.example.loadBooking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity // used this to identify the class a an entity in db
@Table(name = "loads")  // this changes table name in db
@Data   // creates default getters, setters and toString
@NoArgsConstructor  // creates no-arg constructor
@AllArgsConstructor // creates all-args constructor
@Builder    // helps in easy instance creation
public class Load {

    @Id //makes id primary key
    @GeneratedValue // automatically generates a value for this
    private UUID id;

    private String shipperId;

    @Embedded   // facility is a complex data type so use @Embeddable concept
    private Facility facility;

    private String productType;

    private String truckType;

    private int noOfTrucks;

    private double weight;

    private String comment;

    private LocalDateTime datePosted;

    @Enumerated(EnumType.STRING)    // makes enum values as string in db
    private Status status;

    @PrePersist //sets date and time
    public void prePersist() {
        this.datePosted = LocalDateTime.now();
        this.status = Status.POSTED;
    }

    public enum Status {
        POSTED,
        BOOKED,
        CANCELLED
    }
}
