package uni.fmi.RealEstate.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RATINGS")
public class Rating extends MainModel {
    @Column(name = "RATING_VALUE")
    private int value;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "APARTMENT_ID")
    private Apartment apartment;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
