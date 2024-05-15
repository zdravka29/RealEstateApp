package uni.fmi.RealEstate.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.RealEstate.dto.RatingDTO;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.models.Rating;
import uni.fmi.RealEstate.models.User;
import uni.fmi.RealEstate.services.ApartmentService;
import uni.fmi.RealEstate.services.RatingService;
import uni.fmi.RealEstate.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<RatingDTO> findAll() {
        return ratingService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{ratingId}")
    public RatingDTO getBy(@PathVariable(name = "ratingId") long ratingId) {
        return convertToDTO(ratingService.getEntity(ratingId).get());
    }

    @PostMapping()
    public RatingDTO create(@RequestBody RatingDTO apartmentDTO) {
        Rating rating = convertToModel(apartmentDTO);
        rating.setId(null);
        return convertToDTO(ratingService.create(rating));
    }

    @PutMapping()
    public RatingDTO update(@RequestBody RatingDTO apartmentDTO) {
        Rating rating = convertToModel(apartmentDTO);
        return convertToDTO(ratingService.update(rating));
    }

    @DeleteMapping(path = "/{ratingId}")
    public ResponseEntity<String> remove(@PathVariable(name = "ratingId") long ratingId) {

        boolean isRemoved = ratingService.remove(ratingId);

        String deletedMessage = "Rating with id: '" + ratingId + "' was deleted!";
        String notDeletedMessage = "Rating with id: '" + ratingId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    @GetMapping(path = "/list")
    public List<RatingDTO> list(@RequestParam(name = "userId", required = false) Long userId,
                                @RequestParam(name = "apartmentId", required = false) Long apartmentId) {
        List<Rating> ratings = new ArrayList<>();
        if (apartmentId != null && userId != null) {
            ratings = ratingService.findAllByApartmentIdAndUserId(apartmentId, userId);
        }
        if (apartmentId != null) {
            ratings = ratingService.findAllByApartmentId(apartmentId);
        }
        if (userId != null) {
            ratings =  ratingService.findAllByUserId(userId);
        }
        return ratings.stream().map(this::convertToDTO).toList();
    }

    private RatingDTO convertToDTO(Rating rating) {
        RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);
        ratingDTO.setUserId(rating.getUser().getId());
        ratingDTO.setApartmentId(rating.getApartment().getId());
        return ratingDTO;
    }

    private Rating convertToModel(RatingDTO ratingDTO) {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        User user = userService.getEntity(ratingDTO.getUserId())
                .orElseThrow(() -> new IllegalStateException("wrong user id"));
        Apartment apartment = apartmentService.getEntity(ratingDTO.getApartmentId())
                .orElseThrow(() -> new IllegalStateException("wrong product id"));
        rating.setUser(user);
        rating.setApartment(apartment);
        return rating;
    }
}
