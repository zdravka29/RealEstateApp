package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.Apartment;

public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
}
