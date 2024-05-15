package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.FaveApartment;

public interface FaveApartmentRepo extends JpaRepository<FaveApartment, Long> {
}
