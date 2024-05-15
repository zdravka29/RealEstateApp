package uni.fmi.RealEstate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.User;
import uni.fmi.RealEstate.repo.UserRepo;

@Service
public class UserService extends BaseService<User> {
    @Autowired
    UserRepo userRepo;

    @Override
    protected JpaRepository<User, Long> getRepo() {return userRepo;}

}
