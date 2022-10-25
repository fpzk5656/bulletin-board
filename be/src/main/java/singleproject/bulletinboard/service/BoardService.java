package singleproject.bulletinboard.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import singleproject.bulletinboard.domain.Article;
import singleproject.bulletinboard.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final ArticleRepository articleRepository;

	public void write(Article article) {
		articleRepository.save(article);
	}

	public List<Article> findArticles() {
		return articleRepository.findAll();
	}

	public Optional<Article> findById(Long id) {
		return articleRepository.findById(id);
	}
}
