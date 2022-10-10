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
public class Member {

	private Long id;
	private String name;
	private String password;
	private int age;
	private int point;
	private List<Article> articles;
	private List<Comment> comments;
	private List<Member> friends;
	private LocalDateTime birthday;
}
