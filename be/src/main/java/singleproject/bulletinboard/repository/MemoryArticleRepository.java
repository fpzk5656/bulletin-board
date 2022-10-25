package singleproject.bulletinboard.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import singleproject.bulletinboard.domain.Article;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

	private final ConcurrentHashMap<Long, Article> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong();

	@Override
	public Article save(Article article) {

		Long newId = sequence.incrementAndGet();
		Article newArticle = Article.builder()
			.id(newId)
			.writer(article.getWriter())
			.title(article.getTitle())
			.content(article.getContent())
			.createdTime(LocalDateTime.now())
			.build();
		store.put(newId, newArticle);

		return newArticle;
	}

	@Override
	public List<Article> findByMember(String name) {
		return store.values().stream()
			.filter(a -> a.getWriter().getName().equals(name))
			.collect(Collectors.toList());
	}

	@Override
	public List<Article> findAll() {
		return new ArrayList<>(store.values());
	}

	@Override
	public void delete(Article article) {
		store.remove(article.getId());
	}

	@Override
	public Optional<Article> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Article> findByName(String name) {
		return store.values().stream()
			.filter(article -> article.isSameWriterName(name))
			.findAny();
	}
}
