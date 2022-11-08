package singleproject.bulletinboard.domain;

import java.time.LocalDate;
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
	private Integer age;
	private Integer point;
	private List<Article> articles;
	private List<Comment> comments;
	private List<Member> friends;
	private LocalDate birthday;

	public boolean isSameName(String name) {
		return this.name.equals(name);
	}
}
