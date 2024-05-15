package uni.fmi.RealEstate.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "APARTMENTS")
public class Apartment extends MainModel {
    @Column(length = 256, name = "NAME", nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = true)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User owner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    private ApartmentCategory apartmentCategory;
}
