package singleproject.bulletinboard.controller;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestJoinUserInfo {

	private String name;
	private String password;
	private String email;
	private Integer age;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
}
