package singleproject.bulletinboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import singleproject.bulletinboard.controller.SessionConst;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {

		String requestURI = request.getRequestURI();
		log.info("인증 체크 인터셉터 실행 {}", requestURI);

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
			log.info("미인증 사용자 요청");
			// 로그인으로 redirect (인터셉터나 컨트롤러를 호출하지 않고 redirect로 응답한다
			response.sendRedirect("/login?redirectURL=" + requestURI);
			return false;
		}

		return true;
	}
}
