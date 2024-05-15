package uni.fmi.RealEstate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.repo.ApartmentRepo;

@Service
public class ApartmentService extends BaseService<Apartment> {
    @Autowired
    private ApartmentRepo apartmentRepo;
    @Override
    protected JpaRepository<Apartment, Long> getRepo() {return apartmentRepo;}
}
