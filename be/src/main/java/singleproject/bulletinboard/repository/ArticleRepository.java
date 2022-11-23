package singleproject.bulletinboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import singleproject.bulletinboard.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
