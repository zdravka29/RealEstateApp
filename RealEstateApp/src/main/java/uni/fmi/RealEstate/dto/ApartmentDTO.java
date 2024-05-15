package uni.fmi.RealEstate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApartmentDTO extends BaseDTO{
    private String name;
    private double price;
    private String description;
    private long userId;
    private long apartmentCategoryId;
}
