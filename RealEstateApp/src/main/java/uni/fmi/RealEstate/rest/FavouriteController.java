package uni.fmi.RealEstate.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.RealEstate.dto.FavouriteDTO;
import uni.fmi.RealEstate.models.Favourite;
import uni.fmi.RealEstate.models.User;
import uni.fmi.RealEstate.services.FavouriteService;
import uni.fmi.RealEstate.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<FavouriteDTO> findAll() {
        return favouriteService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{favouriteId}")
    public FavouriteDTO getBy(@PathVariable(name = "favouriteId") long articleId) {
        return convertToDTO(favouriteService.getEntity(articleId).get());
    }

    @PostMapping()
    public FavouriteDTO create(@RequestBody FavouriteDTO favouriteDTO) {
        Favourite favourite = convertToModel(favouriteDTO);
        favourite.setId(null);
        return convertToDTO(favouriteService.create(favourite));
    }

    @PutMapping()
    public FavouriteDTO update(@RequestBody FavouriteDTO favouriteDTO) {
        Favourite favourite = convertToModel(favouriteDTO);
        return convertToDTO(favouriteService.update(favourite));
    }

    @DeleteMapping(path = "/{favouriteId}")
    public ResponseEntity<String> remove(@PathVariable(name = "favouriteId") long favouriteId) {

        boolean isRemoved = favouriteService.remove(favouriteId);

        String deletedMessage = "Cart with id: '" + favouriteId + "' was deleted!";
        String notDeletedMessage = "Cart with id: '" + favouriteId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }
    private Favourite convertToModel (FavouriteDTO favouriteDTO) {
        Favourite favourite = modelMapper.map(favouriteDTO, Favourite.class);
        User owner = userService.getEntity(favouriteDTO.getOwnerId())
                .orElseThrow(() -> new IllegalStateException("wrong owner id"));
        favourite.setUser(owner);
        return favourite;
    }

    private FavouriteDTO convertToDTO(Favourite favourite) {
        FavouriteDTO favouriteDTO = modelMapper.map(favourite, FavouriteDTO.class);
        favouriteDTO.setOwnerId(favourite.getUser().getId());
        return favouriteDTO;
    }


}
