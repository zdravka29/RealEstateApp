package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.fmi.RealEstate.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
