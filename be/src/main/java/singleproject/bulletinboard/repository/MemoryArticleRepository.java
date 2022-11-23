package singleproject.bulletinboard.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import singleproject.bulletinboard.domain.Article;

public class MemoryArticleRepository {

	private final ConcurrentHashMap<Long, Article> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong();

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

	public List<Article> findByMember(String name) {
		return store.values().stream()
			.filter(a -> a.getWriter().getName().equals(name))
			.collect(Collectors.toList());
	}

	public List<Article> findAll() {
		return new ArrayList<>(store.values());
	}

	public void delete(Article article) {
		store.remove(article.getId());
	}

	public Optional<Article> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public Optional<Article> findByName(String name) {
		return store.values().stream()
			.filter(article -> article.isSameWriterName(name))
			.findAny();
	}

	public void deleteById(Long id) {
		store.remove(id);
	}
}
