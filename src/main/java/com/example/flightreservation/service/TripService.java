package com.example.flightreservation.service;


import com.example.flightreservation.dto.FlightDto;
import com.example.flightreservation.entity.Trip;
import com.example.flightreservation.exception.ResourceNotFoundException;
import com.example.flightreservation.repository.TripRepository;
import com.example.flightreservation.service.IServices.ITripService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class TripService implements ITripService {

    private TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public List<Trip> viewTrips() {
        return tripRepository.findAll();
    }

    @Override
    public Trip viewTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Trip not found"));
    }

    @Override
    public Trip updateTripById(Trip trip, Long id) {
        Trip existingTrip = tripRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Trip not found"));
        existingTrip.setTripDescription(trip.getTripDescription());
        existingTrip.setFromLocation(trip.getFromLocation());
        existingTrip.setToLocation(trip.getToLocation());
        existingTrip.setDepartureDate(trip.getDepartureDate());
        existingTrip.setArrivalDate(trip.getArrivalDate());

        tripRepository.save(existingTrip);
        return existingTrip;
    }

    @Override
    public void deleteTripById(Long id) {
        tripRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Trip not found"));
        tripRepository.deleteById(id);
    }

    @Override
    public List<FlightDto>viewFlights(){
        return tripRepository.findAll()
                .stream().map(this::convertTripEntityToDto)
                .collect(Collectors.toList());

    }
    private FlightDto convertTripEntityToDto(Trip trip){
        FlightDto flightDto = new FlightDto();

        flightDto.setArrivalDate(trip.getArrivalDate());
        flightDto.setDepartureDate(trip.getDepartureDate());
        flightDto.setFromLocation(trip.getFromLocation());
        flightDto.setToLocation(trip.getToLocation());

        return flightDto;
    }


}
