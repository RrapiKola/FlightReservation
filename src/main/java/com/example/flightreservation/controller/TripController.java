package com.example.flightreservation.controller;
import com.example.flightreservation.dto.FlightDto;
import com.example.flightreservation.entity.Trip;
import com.example.flightreservation.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/trip")
public class TripController {

    private TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        return new ResponseEntity<Trip>(tripService.createTrip(trip), HttpStatus.CREATED);
    }

    @GetMapping("/viewDto")
    @PreAuthorize("hasAuthority('USER')")
    public List<FlightDto>viewFlightsDto(){
        return tripService.viewFlights();
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Trip> viewTrips() {
        return tripService.viewTrips();
    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Trip> viewTripById(@PathVariable Long id) {
        return new ResponseEntity<Trip>(tripService.viewTripById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Trip> updateTripById(@PathVariable("id") Long id, @RequestBody Trip trip) {
        return new ResponseEntity<Trip>(tripService.updateTripById(trip, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> deleteTripById(@PathVariable("id") Long id) {
        tripService.deleteTripById(id);
        return new ResponseEntity<String>("Trip Deleted", HttpStatus.OK);
    }

}
