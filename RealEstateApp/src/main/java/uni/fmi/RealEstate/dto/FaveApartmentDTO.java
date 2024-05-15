package uni.fmi.RealEstate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaveApartmentDTO extends BaseDTO{
    private long favouriteId;
    private long apartmentId;
}
