package uni.fmi.RealEstate.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends MainModel {
    @Column(nullable = false, length = 256)
    private String username;
    @Column(nullable = false, length = 256)
    private String password;
    @Column(nullable = false, length = 256, unique = true)
    private String email;
    @OneToMany(mappedBy = "owner")
    private Set<Article> articles;
}
