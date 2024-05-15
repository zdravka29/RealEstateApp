package uni.fmi.RealEstate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RatingDTO extends BaseDTO{
    private int value;
    private String comment;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private long apartmentId;
    private long userId;
}
