package singleproject.bulletinboard.controller;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import singleproject.bulletinboard.domain.user.Member;

@AllArgsConstructor
@Getter
public class ResponseUserInfo {

	private Long id;
	private String name;
	private String password;
	private Integer age;
	private Integer point;
	private LocalDate birthday;

	public static ResponseUserInfo from(Member member) {

		return new ResponseUserInfo(member.getId(), member.getName(), member.getPassword(),
			member.getAge(), member.getPoint(), member.getBirthday());
	}
}
