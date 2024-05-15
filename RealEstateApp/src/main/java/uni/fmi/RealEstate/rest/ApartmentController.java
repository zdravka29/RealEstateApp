package uni.fmi.RealEstate.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.RealEstate.dto.ApartmentDTO;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.models.ApartmentCategory;
import uni.fmi.RealEstate.services.ApartmentCategoryService;
import uni.fmi.RealEstate.services.ApartmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    ApartmentCategoryService apartmentCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ApartmentDTO> findAll() {
        List<Apartment> products = apartmentService.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{apartmentId}")
    public ApartmentDTO getBy(@PathVariable(name = "apartmentId") long apartmentId) {
        Optional<Apartment> optionalProduct = apartmentService.getEntity(apartmentId);
        return optionalProduct.map(this::convertToDTO).orElse(null);
    }

    @PostMapping()
    public ApartmentDTO create(@RequestBody ApartmentDTO apartmentDTO) {
        Apartment apartment = convertToModel(apartmentDTO);
        apartment.setId(null);
        return convertToDTO(apartmentService.create(apartment));
    }

    @PutMapping()
    public ApartmentDTO update(@RequestBody ApartmentDTO apartmentDTO) {
        Apartment updated = apartmentService.update(convertToModel(apartmentDTO));
        return convertToDTO(updated);
    }

    @DeleteMapping(path = "/{apartmentId}")
    public ResponseEntity<String> remove(@PathVariable(name = "apartmentId") long apartmentId) {

        boolean isRemoved = apartmentService.remove(apartmentId);

        String deletedMessage = "Product with id: '" + apartmentId + "' was deleted!";
        String notDeletedMessage = "Product with id: '" + apartmentId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    private ApartmentDTO convertToDTO(Apartment apartment) {
        return modelMapper.map(apartment, ApartmentDTO.class);
    }

    private Apartment convertToModel(ApartmentDTO apartmentDTO){
        Apartment apartment = modelMapper.map(apartmentDTO, Apartment.class);
        if(apartmentDTO.getApartmentCategoryId()>0){
            Optional<ApartmentCategory> optionalApartmentCategory = apartmentCategoryService.getEntity(apartmentDTO.getApartmentCategoryId());
            if(optionalApartmentCategory.isPresent()){
                apartment.setApartmentCategory(optionalApartmentCategory.get());
            }else {
                throw new IllegalStateException("The category with id: '"+apartmentDTO.getApartmentCategoryId()+"' does not exist!");
            }
        }
        return apartment;
    }
}
