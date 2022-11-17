package singleproject.bulletinboard.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
