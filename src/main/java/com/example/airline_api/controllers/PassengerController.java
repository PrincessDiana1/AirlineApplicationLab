package com.example.airline_api.controllers;

import com.example.airline_api.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.PassengerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    // Display details of all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers
    (@RequestParam (required = false, name = "email")
     ){
        if(email != null){
            return new ResponseEntity<>(passengerService.findAllPassenger(email), HttpStatus.OK);
        }
        return new ResponseEntity<>(passengerService.findAllPassenger(), HttpStatus.OK);

    }


    // Display specific passenger details
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Passenger>> getPassengerById(@PathVariable Long id){
        Passenger foundPassenger = passengerService.findPassenger(id);
        return new ResponseEntity(foundPassenger, HttpStatus.OK);
    }

    // Add a new passenger
    @PostMapping
    public ResponseEntity<List<Passenger>> addNewPassenger(@RequestBody Passenger passenger){
        passengerService.savePassenger(passenger);
        return new ResponseEntity<>(passengerService.findAllPassenger(), HttpStatus.CREATED);
    }

}
