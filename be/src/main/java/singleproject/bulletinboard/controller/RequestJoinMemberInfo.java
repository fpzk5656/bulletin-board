package singleproject.bulletinboard.controller;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestJoinMemberInfo {

	private String name;
	private String password;
	private int age;
	private LocalDateTime birthday;
}
