package singleproject.bulletinboard.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.Getter;
import singleproject.bulletinboard.domain.Comment;
import singleproject.bulletinboard.domain.Member;

@Getter
public class ResponseArticleInfo {

	private Long id;
	private Member writer;
	private String title;
	private String content;
	private String createdTime;
	private List<Comment> comments;

	public ResponseArticleInfo(Long id, Member writer, String title, String content,
		LocalDateTime createdTime, List<Comment> comments) {

		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime.truncatedTo(ChronoUnit.SECONDS)
			.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		this.comments = comments;
	}
}
