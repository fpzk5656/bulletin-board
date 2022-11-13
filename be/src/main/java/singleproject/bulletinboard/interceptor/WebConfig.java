package singleproject.bulletinboard.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

	public static final Integer OrderOfCallFromInterceptor = 1;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 인터셉터를 등록한다
		registry.addInterceptor(new LoginCheckInterceptor())
			.order(OrderOfCallFromInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/", "/singup", "/login", "/logout", "/board", "/*.ico", "/css/**",
				"/error");
	}
}
