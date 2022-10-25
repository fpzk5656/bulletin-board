package singleproject.bulletinboard.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestCreatedArticleInfo {

	private String title;
	private String content;

	// TODO 작성자 정보도 언젠가 필요함
}
