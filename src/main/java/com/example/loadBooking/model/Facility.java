package com.example.loadBooking.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable // because Facility is a complex data type
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facility {
    private String loadingPoint;
    private String unloadingPoint;
    private LocalDateTime loadingDate;
    private LocalDateTime unloadingDate;
}
