package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
