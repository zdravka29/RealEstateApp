package uni.fmi.RealEstate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.Article;

public interface ArticleRepo extends JpaRepository<Article, Long> {
}
