package singleproject.bulletinboard.repository;

import java.util.List;
import java.util.Optional;
import singleproject.bulletinboard.domain.Article;

public interface ArticleRepository {

	Article save(Article article);
	List<Article> findByMember(String name);
	List<Article> findAll();
	void delete(Article article);
	Optional<Article> findById(Long id);
	Optional<Article> findByName(String name);
	void deleteById(Long id);
}
