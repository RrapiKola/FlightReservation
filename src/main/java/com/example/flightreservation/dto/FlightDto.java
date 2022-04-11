package com.example.flightreservation.dto;

import lombok.Data;

@Data
public class FlightDto {

    private String fromLocation;
    private String toLocation;
    private String departureDate;
    private String arrivalDate;
}
