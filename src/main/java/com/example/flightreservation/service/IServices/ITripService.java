package com.example.flightreservation.service.IServices;


import com.example.flightreservation.dto.FlightDto;
import com.example.flightreservation.entity.Trip;

import java.util.List;

public interface ITripService {

    Trip createTrip(Trip trip);

    List<Trip> viewTrips();

    Trip viewTripById(Long id);

    Trip updateTripById(Trip trip,Long id);

    void deleteTripById(Long id);


    List<FlightDto>viewFlights();
}
