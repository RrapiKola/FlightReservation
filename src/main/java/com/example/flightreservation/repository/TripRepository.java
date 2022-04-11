package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {

}
