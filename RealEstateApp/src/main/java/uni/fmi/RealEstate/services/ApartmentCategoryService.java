package uni.fmi.RealEstate.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.ApartmentCategory;
import uni.fmi.RealEstate.repo.ApartmentCategoryRepo;

@Service
@AllArgsConstructor
public class ApartmentCategoryService extends BaseService<ApartmentCategory> {
    ApartmentCategoryRepo apartmentCategoryRepo;

    @Override
    protected JpaRepository<ApartmentCategory, Long> getRepo() {return apartmentCategoryRepo;}
}
