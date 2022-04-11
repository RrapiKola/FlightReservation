package com.example.flightreservation.entity;
import com.example.flightreservation.entity.enumerations.Status;
import com.example.flightreservation.entity.enumerations.TripReason;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.DateType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String tripDescription;


    @Column(nullable = false)
    private String fromLocation;

    @Column(nullable = false)
    private String toLocation;

    @Column(nullable = false)
    private String departureDate;

    @Column(nullable = false)
    private String arrivalDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TripReason tripReason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;


}
