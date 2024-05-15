package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.Email;

import java.util.List;

public interface EmailRepo extends JpaRepository<Email, Long> {
    List<Email> findByCompanyNameContaining(String filter);
}
