package uni.fmi.RealEstate.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.RealEstate.dto.ApartmentCategoryDTO;
import uni.fmi.RealEstate.models.ApartmentCategory;
import uni.fmi.RealEstate.services.ApartmentCategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/apartmentCategory")
public class ApartmentCategoryRestController {

    private final ApartmentCategoryService apartmentCategoryService;
    private final ModelMapper modelMapper;

    public ApartmentCategoryRestController(ApartmentCategoryService apartmentCategoryService, ModelMapper modelMapper){
        this.apartmentCategoryService = apartmentCategoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<ApartmentCategoryDTO> list() {
        List<ApartmentCategory> apartmentCategories = apartmentCategoryService.findAll();
        return apartmentCategories
                .stream()
                .map(this::convertToApartmentCategoryDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{apartmentCategoryId}")
    public ApartmentCategoryDTO getCategory(@PathVariable(name = "apartmentCategoryId") long apartmentCategoryId) {

        Optional<ApartmentCategory> optionalApartmentCategory = apartmentCategoryService.getEntity(apartmentCategoryId);
       /* if(optionalCategory.isPresent()){
            result = convertToCategoryDTO(optionalCategory.get());
        }else{
            result = null;
        }
        return result;*/
        return optionalApartmentCategory.map(this::convertToApartmentCategoryDTO).orElse(null);
    }

    @PostMapping()
    public ApartmentCategoryDTO create(@RequestBody ApartmentCategoryDTO newApartmentCategory) {
        ApartmentCategory apartmentCategory = convertApartmentCategoryDtoToModel(newApartmentCategory);
        return convertToApartmentCategoryDTO(apartmentCategoryService.create(apartmentCategory));
    }

    @PutMapping()
    public ApartmentCategoryDTO update(@RequestBody ApartmentCategoryDTO newApartmentCategory) {
        ApartmentCategory apartmentCategory = convertApartmentCategoryDtoToModel(newApartmentCategory);
        return convertToApartmentCategoryDTO(apartmentCategoryService.update(apartmentCategory));
    }

    @DeleteMapping(path = "/{apartmentCategoryId}")
    public ResponseEntity<String> remove(@PathVariable(name = "apartmentCategoryId") long apartmentCategoryId) {

        boolean isRemoved = apartmentCategoryService.remove(apartmentCategoryId);

        String deletedMessage = "Category with id: '" + apartmentCategoryId + "' was deleted!";
        String notDeletedMessage = "Category with id: '" + apartmentCategoryId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity(deletedMessage, HttpStatusCode.valueOf(200)):
                new ResponseEntity(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    private ApartmentCategoryDTO convertToApartmentCategoryDTO(ApartmentCategory apartmentCategory) {
        final ApartmentCategoryDTO result = modelMapper.map(apartmentCategory, ApartmentCategoryDTO.class);
        return result;
    }

    private ApartmentCategory convertApartmentCategoryDtoToModel(ApartmentCategoryDTO newApartmentCategory) {
        ApartmentCategory apartmentCategory = modelMapper.map(newApartmentCategory, ApartmentCategory.class);
        return apartmentCategory;
    }

}
