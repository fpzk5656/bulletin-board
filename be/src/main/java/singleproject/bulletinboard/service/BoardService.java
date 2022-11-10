package singleproject.bulletinboard.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import singleproject.bulletinboard.domain.Article;
import singleproject.bulletinboard.repository.ArticleRepository;
import singleproject.bulletinboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final ArticleRepository articleRepository;
	private final MemberRepository memberRepository;

	public void write(String title, String content, Long userId) {

		articleRepository.save(Article.builder()
			.title(title)
			.content(content)
			.writer(memberRepository.findById(userId).orElseThrow())
			.build());
	}

	public List<Article> findArticles() {
		return articleRepository.findAll();
	}

	public Optional<Article> findById(Long id) {
		return articleRepository.findById(id);
	}
}
