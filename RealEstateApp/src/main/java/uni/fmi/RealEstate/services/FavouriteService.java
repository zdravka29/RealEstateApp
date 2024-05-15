package uni.fmi.RealEstate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.Favourite;
import uni.fmi.RealEstate.repo.FavouriteRepo;

@Service
public class FavouriteService extends BaseService<Favourite>{
    @Autowired
    private FavouriteRepo favouriteRepo;

    @Override
    protected JpaRepository<Favourite, Long> getRepo() {return favouriteRepo;}
}
