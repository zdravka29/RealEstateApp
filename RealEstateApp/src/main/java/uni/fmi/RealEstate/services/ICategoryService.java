package uni.fmi.RealEstate.services;

import uni.fmi.RealEstate.dto.ApartmentCategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<ApartmentCategoryDTO> getAll();
    ApartmentCategoryDTO getBy(Long id);
    ApartmentCategoryDTO create(ApartmentCategoryDTO apartmentCategoryDTO);
    ApartmentCategoryDTO update(ApartmentCategoryDTO apartmentCategoryDTO);
    boolean remove(long id);
}
