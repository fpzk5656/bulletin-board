package singleproject.bulletinboard.controller;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import singleproject.bulletinboard.domain.Article;
import singleproject.bulletinboard.domain.Comment;
import singleproject.bulletinboard.domain.Member;

@AllArgsConstructor
@Getter
public class ResponseArticleInfo {

	private Long id;
	private Member writer;
	private String title;
	private String content;
	private String createdTime;
	private List<Comment> comments;

	public static ResponseArticleInfo from(Article article) {
		return new ResponseArticleInfo(article.getId(),
			article.getWriter(),
			article.getTitle(),
			article.getContent(),
			article.getCreatedTime().truncatedTo(ChronoUnit.SECONDS)
				.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), article.getComments());
	}
}
