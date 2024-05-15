package uni.fmi.RealEstate.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FAVEAPARTMENTS")
public class FaveApartment extends MainModel{
    @ManyToOne
    @JoinColumn(name = "FAVOURITE_ID")
    private Favourite favourite;
    @ManyToOne
    @JoinColumn(name = "APARTMENT_ID")
    private Apartment apartment;
}
