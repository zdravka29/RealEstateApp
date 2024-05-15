package uni.fmi.RealEstate.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FAVOURITES")
public class Favourite extends MainModel{
    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;
}
