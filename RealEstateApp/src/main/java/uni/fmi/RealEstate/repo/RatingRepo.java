package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.models.Rating;
import uni.fmi.RealEstate.models.User;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating, Long> {

    List<Rating> findByUser(final User owner);
    List<Rating> findByApartment(final Apartment relatedApartment);
    List<Rating> findByApartmentAndUser(Apartment apartment, User user);
}
