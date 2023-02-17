package repositories;

import com.example.airline_api.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

    // find all passengers

    List<Passenger> findEmail(String email);
}
