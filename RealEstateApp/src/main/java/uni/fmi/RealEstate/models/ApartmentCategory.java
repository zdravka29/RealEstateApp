package uni.fmi.RealEstate.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "APARTMENT_CATEGORY")
public class ApartmentCategory extends MainModel {
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "apartmentCategory")
    private Set<Apartment> apartments;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id")
    private ApartmentCategory parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<ApartmentCategory> children;

    public boolean addApartment(final Apartment apartment){
        if(apartments == null){
            apartments = new HashSet<>();
        }
        return apartments.add(apartment);
    }
}
