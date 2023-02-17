package services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.FlightRepository;
import repositories.PassengerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public Passenger updatePassenger(Passenger passenger, Long id){
        Passenger passengerToUpdate = passengerRepository.findById(id).get();
        passengerToUpdate.setName(passenger.getName());
        passengerToUpdate.setEmail(passenger.getEmail());
        passengerToUpdate.setFlights(new ArrayList<Flight>());
        return passenger;
    }


    public Passenger findPassenger (Long id){
        return passengerRepository.findBy(id).get();
    }

    public List<Passenger> findAllPassenger(){
        return passengerRepository.findAll();
    }

    public List<Passenger> findAllPassengerOverEmail (String email){
        return passengerRepository.findByPassengerOverEmail(email);
    }

    public void deletePassenger(Long id){
        PassengerRepository.deleteById(id);
    }

}
