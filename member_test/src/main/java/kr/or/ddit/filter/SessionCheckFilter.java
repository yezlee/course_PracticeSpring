package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.vo.UserVo;

//세션이 없으면 로그인페이지로 가게끔 도와주는 필터
public class SessionCheckFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//요청 URI가 S_USER 속성이 세션에 있어야 하는 주소인지 체크
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		
		//세션 체크가 필요없는 주소 : login.jsp, loginController
		if(uri.endsWith("login.jsp") || uri.endsWith("loginController") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/image/"))  {
			chain.doFilter(request, response);
		}
		//세션 체크가 필요한 주소(S_USER 속성 확인))
		else {
			UserVo user = (UserVo)req.getSession().getAttribute("S_USER");
			
			//user 정보가 NULL ==> 로그인 안한 상태 ==> 로그인 페이지 이동
			if(user==null) {
				((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/login.jsp");
			}
			
			//user 정보가 !NULL ==> 로그인 한 상태 ==> 요청처리
			else {
				chain.doFilter(request, response);
			}
		}
		
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
	}


}
