package singleproject.bulletinboard.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
	private String password;
	private Integer age;
	private Integer point;

	@OneToMany(mappedBy = "writer")
	private List<Article> articles = new ArrayList<>();

	// TODO 댓글
//	private List<Comment> comments;

	// TODO 친구
//	private List<Member> friends = new ArrayList<>();
	private LocalDate birthday;

	public boolean isSameName(String name) {
		return this.name.equals(name);
	}
}
