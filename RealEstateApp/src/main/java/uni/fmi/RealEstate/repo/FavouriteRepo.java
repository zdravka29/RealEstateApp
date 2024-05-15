package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.Favourite;

public interface FavouriteRepo extends JpaRepository<Favourite, Long> {
}
