package uni.fmi.RealEstate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.FaveApartment;
import uni.fmi.RealEstate.repo.FaveApartmentRepo;

@Service
public class FaveApartmentService extends BaseService<FaveApartment>{
    @Autowired
    FaveApartmentRepo faveApartmentRepo;

    @Override
    protected JpaRepository<FaveApartment, Long> getRepo() {return faveApartmentRepo;}
}
