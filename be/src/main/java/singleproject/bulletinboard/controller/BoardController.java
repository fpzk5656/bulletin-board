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
import singleproject.bulletinboard.config.auth.dto.SessionUser;
import singleproject.bulletinboard.domain.Article;
import singleproject.bulletinboard.service.BoardService;
import singleproject.bulletinboard.service.MemberService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final MemberService memberService;

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

	@GetMapping("/board/article/{id}")
	public String viewArticle(@PathVariable Long id, Model model) {

		log.info("article view");

		Article article = boardService.findById(id).orElseThrow();
		model.addAttribute("article", ResponseArticleInfo.from(article));

		return "page/article";
	}

	@GetMapping("/board/article/write")
	public String writeArticleForm(
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) SessionUser loginMember) {

		// 로그인 유저가 아니면 글을 작성할 수 없다.
		if (loginMember == null) {
			log.info("로그인을 하지 않으면 글을 작성할 수 없습니다.");
			return "redirect:/board";
		}

		return "page/article_write";
	}

	@PostMapping("/board/article/create")
	public String createArticle(@Valid @ModelAttribute RequestCreatedArticleInfo createdArticleInfo,
		BindingResult bindingResult,
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) SessionUser loginMember,
		RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			log.info("errors  = {}", bindingResult);
			return "board/article_write";
		}

		// 로그인 유저가 아니면 글을 작성할 수 없다.
		if (loginMember == null) {
			log.info("로그인을 하지 않으면 글을 작성할 수 없습니다.");
			return "redirect:/board";
		}
		// 임시
		Long articleId = boardService.write(createdArticleInfo.getTitle(),
			createdArticleInfo.getContent(),
			memberService.findByName(loginMember.getName()).orElseThrow().getId());
		redirectAttributes.addAttribute("articleId", articleId);

		return "redirect:/board/article/{articleId}";
	}

	@GetMapping("/board/{articleId}/edit")
	public String editForm(@PathVariable Long articleId, Model model,
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) SessionUser loginMember) {

		if (boardService.checkWriterName(loginMember.getName(), articleId) == false) {
			log.info("해당 글을 수정할 권한이 없습니다.");

			return "redirect:/board/article/{articleId}";
		}
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
		@Valid @ModelAttribute RequestCreatedArticleInfo articleForm, BindingResult bindingResult,
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) SessionUser loginMember) {

		if (bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult);
			return "page/editForm";
		}

		if (boardService.checkWriterName(loginMember.getName(), articleId) == false) {
			log.info("해당 글을 수정할 권한이 없습니다.");

			return "redirect:/board/article/{articleId}";
		}

		boardService.updateArticle(articleId, articleForm.getTitle(), articleForm.getContent());

		return "redirect:/board/article/{articleId}";
	}

	@PostMapping("/board/{articleId}/delete")
	public String delete(@PathVariable Long articleId,
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) SessionUser loginMember) {

		if (boardService.checkWriterName(loginMember.getName(), articleId) == false) {
			log.info("해당 글을 삭제할 권한이 없습니다.");

			return "redirect:/board/article/{articleId}";
		}

		boardService.deleteById(articleId);
		return "redirect:/board";
	}
}
