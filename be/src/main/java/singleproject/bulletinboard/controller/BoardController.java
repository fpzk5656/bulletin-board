package singleproject.bulletinboard.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import singleproject.bulletinboard.domain.Article;
import singleproject.bulletinboard.service.BoardService;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/board")
	public String board(Model model) {

		List<ResponseArticleInfo> articles = boardService.findArticles().stream()
			.map(article -> ResponseArticleInfo.from(article))
			.collect(Collectors.toList());

		model.addAttribute("articles", articles);
		return "page/board";
	}

	@GetMapping("/board/article/list")
	public String list(Model model) {
		List<Article> articles = boardService.findArticles();

		model.addAttribute("articles", articles);
		return "page/board_list";
	}

	@GetMapping("/board/article/write")
	public String writeArticleForm() {

		return "page/article_write";
	}

	@GetMapping("/board/article/{id}")
	public String viewArticle(@PathVariable Long id, Model model) {
		Article article = boardService.findById(id).orElseThrow();
		model.addAttribute("article", ResponseArticleInfo.from(article));

		return "page/article";
	}

	@PostMapping("/board/article/create")
	public String createArticle(RequestCreatedArticleInfo createdArticleInfo) {

		boardService.write(Article.builder()
			.title(createdArticleInfo.getTitle())
			.content(createdArticleInfo.getContent())
			.build());

		return "redirect:/board";
	}
}
