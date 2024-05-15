package uni.fmi.RealEstate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.Comment;
import uni.fmi.RealEstate.repo.CommentRepo;

@Service
public class CommentService extends BaseService<Comment>{
    @Autowired
    private CommentRepo commentRepo;

    @Override
    protected JpaRepository<Comment, Long> getRepo() {return commentRepo;}
}
