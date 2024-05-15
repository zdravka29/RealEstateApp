package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.fmi.RealEstate.models.ApartmentCategory;
@Repository
public interface ApartmentCategoryRepo extends JpaRepository<ApartmentCategory, Long> {
}
