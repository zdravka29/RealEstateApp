package uni.fmi.RealEstate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.RealEstate.models.Article;
import uni.fmi.RealEstate.repo.ArticleRepo;

@Service
public class ArticleService extends BaseService<Article> {
    @Autowired
    ArticleRepo articleRepo;
    @Override
    protected JpaRepository<Article, Long> getRepo() {return articleRepo;}
}
