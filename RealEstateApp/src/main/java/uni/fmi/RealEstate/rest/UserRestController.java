package uni.fmi.RealEstate.rest;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.RealEstate.dto.UserDTO;
import uni.fmi.RealEstate.models.User;
import uni.fmi.RealEstate.services.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper  modelMapper;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @PostMapping()
    public UserDTO create(UserDTO newUser, HttpSession session) {
        User user = convertToModel(newUser);
        user.setId(null);
        session.setAttribute("isLogged", true);

        return convertToDTO(userService.create(user));
    }

    @GetMapping(path = "isLogged")
    public boolean isLogged(HttpSession session){
        String attr = String.valueOf(session.getAttribute("isLogged"));

        if (attr == null)
            return false;

        return true;
    }

    @PutMapping()
    public UserDTO update(@RequestBody UserDTO user) {
        return convertToDTO(userService.update(convertToModel(user)));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> remove(@PathVariable(name = "userId") long userId){

        boolean isRemoved = userService.remove(userId);

        String deletedMessage = "User with id: '" + userId + "' was deleted!";
        String notDeletedMessage = "User with id: '" + userId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404)) ;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    public User convertToModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
