package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.FlightService;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    //  Handles following:
    //  * GET /flights:
    //  INDEX

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlightsAndFilters
    (@RequestParam(required = false, name= "capacity") Integer capacity
    ){
 //       Get/flights?Capacity=200
        if(capacity != null){
            return new ResponseEntity<>(flightService.findAllFlightsUnderFullCapacity(capacity), HttpStatus.OK);
        }
//        GET/flights
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        Flight foundFlight = flightService.findFlight(id);
        return new ResponseEntity<>(foundFlight, HttpStatus.OK);
    }

    // Add details of a new flight - with or without passengers
    @PostMapping
    public ResponseEntity<List<Flight>> addNewFlight(@RequestBody Flight flight){
        flightService.saveFlight(flight);
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@RequestBody Flight flight, @PathVariable Long id){
        Flight addedPassenger = flightService.updateFlight(flight, id);
        return new ResponseEntity<>(addedPassenger, HttpStatus.OK);
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
