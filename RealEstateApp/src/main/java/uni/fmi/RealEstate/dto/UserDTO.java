package uni.fmi.RealEstate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO{
    private String username;
    private String password;
    private String email;
}
