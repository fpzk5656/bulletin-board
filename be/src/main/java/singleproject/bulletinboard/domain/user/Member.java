package singleproject.bulletinboard.domain.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import singleproject.bulletinboard.domain.Article;

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

	@Column(nullable = false)
	private String name;

	@Column
	private String password;

	@Column(nullable = false)
	private String email;

	@Column
	private String picture;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	private Integer age;
	private Integer point;

	@OneToMany(mappedBy = "writer")
	private List<Article> articles = new ArrayList<>();

	// TODO 댓글
//	private List<Comment> comments;

	// TODO 친구
//	private List<Member> friends = new ArrayList<>();
	private LocalDate birthday;

	public Member update(String name, String picture) {
		this.name = name;
		this.picture = picture;

		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}

	public boolean isSameName(String name) {
		return this.name.equals(name);
	}
}
