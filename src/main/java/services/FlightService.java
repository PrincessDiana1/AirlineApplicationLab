package services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.FlightRepository;
import repositories.PassengerRepository;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public Flight updateFlight(Flight flight, Long id){
        Flight flightToUpdate = flightRepository.findbyId(id).get();
        flightToUpdate.setDestination(flight.getDestination());
        flightToUpdate.setCapacity(flight.getCapacity());
        flightToUpdate.setPassengers(flight.getPassengers());

        flightRepository.save(flightToUpdate);
        return flightToUpdate;
    }

    public void saveFlight(Flight flight){flightRepository.save(flight);
    }

    public Flight addPassengerToFlight(long flightId, Passenger passenger){
        Flight flight = flightRepository.findById(flightId).get();
        List<Passenger> passengers = flight.getPassengers();
        passengers.add(passenger);
        flight.setPassengers(passengers);
        flightRepository.save(flight);
        return flight;
    }

    public Flight findFlight(Long id) {return flightRepository.findById(id).get();
    }

    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    public void deleteFlight(long id){
        this.flightRepository.deleteById(id);
    }


}
