package singleproject.bulletinboard.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreatedArticleInfo {

	@NotBlank(message="제목을 입력해주세요.")
	private String title;
	@NotNull
	private String content;

	// TODO 작성자 정보도 언젠가 필요함
}
