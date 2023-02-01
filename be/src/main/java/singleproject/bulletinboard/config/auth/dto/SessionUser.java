package singleproject.bulletinboard.config.auth.dto;

import java.io.Serializable;
import lombok.Getter;
import singleproject.bulletinboard.domain.user.Member;

@Getter
public class SessionUser implements Serializable {

	private Long id;
	private String name;
	private String email;
	private String picture;

	public SessionUser(Member member) {
		this.id = member.getId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.picture = member.getPicture();
	}
}
