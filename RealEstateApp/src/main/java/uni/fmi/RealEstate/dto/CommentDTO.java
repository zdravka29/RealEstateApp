package uni.fmi.RealEstate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO extends BaseDTO{
    private String text;
    private int rating;
    private long apartmentId;
    private long articleId;
    private long ownerId;
}
