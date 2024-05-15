package uni.fmi.RealEstate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.models.Rating;
import uni.fmi.RealEstate.models.User;
import uni.fmi.RealEstate.repo.RatingRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService extends BaseService<Rating>{
    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ApartmentService apartmentService;

    @Override
    protected JpaRepository<Rating, Long> getRepo() {return ratingRepo; }

    public List<Rating> findAllByUserId(long userId) {
        Optional<User> entity = userService.getEntity(userId);
        return ratingRepo.findByUser(entity.orElseThrow());
    }

    public List<Rating> findAllByApartmentId(long apartmentId) {
        Optional<Apartment> entity = apartmentService.getEntity(apartmentId);
        return ratingRepo.findByApartment(entity.orElseThrow());
    }

    public List<Rating> findAllByApartmentIdAndUserId(Long apartmentId, Long userId) {
        Optional<Apartment> apartment = apartmentService.getEntity(apartmentId);
        Optional<User> user = userService.getEntity(userId);
        return ratingRepo.findByApartmentAndUser(apartment.orElseThrow(), user.orElseThrow());
    }
}
