package singleproject.bulletinboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import singleproject.bulletinboard.domain.Article;
import singleproject.bulletinboard.repository.ArticleRepository;
import singleproject.bulletinboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final ArticleRepository articleRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public Long write(String title, String content, Long userId) {

		Article article = articleRepository.save(Article.builder()
			.title(title)
			.content(content)
			.writer(memberRepository.findById(userId).orElseThrow())
			.createdTime(LocalDateTime.now())
			.build());
		return article.getId();
	}

	public List<Article> findArticles() {
		return articleRepository.findAll();
	}

	public Optional<Article> findById(Long id) {
		return articleRepository.findById(id);
	}

	@Transactional
	public void updateArticle(Long id, String title, String content) {
		Article article = articleRepository.findById(id).orElseThrow();
		article.update(title, content);

		articleRepository.save(article);
	}

	public boolean checkWriterName(String writerName, Long articleId) {

		return articleRepository.findById(articleId).orElseThrow()
			.isSameWriterName(writerName);
	}

	@Transactional
	public void deleteById(Long id) {
		articleRepository.deleteById(id);
	}
}
