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
@Table(name = "ARTICLES")
public class Article extends MainModel {
    private String content;
    private String title;
    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User owner;
}
