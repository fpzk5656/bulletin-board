package singleproject.bulletinboard.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Article {

	private Long id;
	private Member writer;
	private String title;
	private String content;
	private LocalDateTime createdTime;
	private List<Comment> comments;

	public boolean isSameWriterName(String writerName) {
		return this.writer.isSameName(writerName);
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
