package uni.fmi.RealEstate.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EMAILS")
public class Email extends MainModel{
    private String subject;
    private String fromEmail;
    private String body;
    private String companyName;
    private String clientName;
}
