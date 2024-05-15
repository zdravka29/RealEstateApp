package uni.fmi.RealEstate.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment extends MainModel{
    @Column(nullable = false)
    private String text;
    private int rating;
    @ManyToOne
    @JoinColumn(name = "APARTMENT_ID")
    private Apartment apartment;
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;
}
