package singleproject.bulletinboard.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import singleproject.bulletinboard.domain.Article;
import singleproject.bulletinboard.domain.Member;
import singleproject.bulletinboard.service.BoardService;

@Controller
@Slf4j
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

		log.info("article view");

		Article article = boardService.findById(id).orElseThrow();
		model.addAttribute("article", ResponseArticleInfo.from(article));

		return "page/article";
	}

	@PostMapping("/board/article/create")
	public String createArticle(@Valid @ModelAttribute RequestCreatedArticleInfo createdArticleInfo,
		BindingResult bindingResult,
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Member loginUser, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			log.info("errors  = {}", bindingResult);
			return "board/article_write";
		}

		Long articleId = boardService.write(createdArticleInfo.getTitle(), createdArticleInfo.getContent(),
			loginUser.getId());
		redirectAttributes.addAttribute("articleId",articleId);

		return "redirect:/board/article/{articleId}";
	}

	@GetMapping("/board/{articleId}/edit")
	public String editForm(@PathVariable Long articleId, Model model) {
		Article article = boardService.findById(articleId).orElseThrow();

		RequestCreatedArticleInfo articleForm = new RequestCreatedArticleInfo();
		articleForm.setTitle(article.getTitle());
		articleForm.setContent(article.getContent());

		model.addAttribute("articleForm", articleForm);
		model.addAttribute("articleId", articleId);

		return "page/editForm";
	}

	@PostMapping("/board/{articleId}/edit")
	public String edit(@PathVariable Long articleId,
		@Valid @ModelAttribute RequestCreatedArticleInfo articleForm, BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			log.info("errors = {}", bindingResult);
			return "page/editForm";
		}

		boardService.updateArticle(articleId, articleForm.getTitle(), articleForm.getContent());

		return "redirect:/board/article/{articleId}";
	}

	@GetMapping("/board/{articleId}/delete")
	public String delete(@PathVariable Long articleId) {
		boardService.deleteById(articleId);
		return "redirect:/board";
	}
}
