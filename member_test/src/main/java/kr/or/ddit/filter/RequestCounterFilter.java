package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestCounterFilter implements Filter{

	Map<String, Integer> requestCountMap = new HashMap<String, Integer>();
	private static final Logger logger = LoggerFactory.getLogger(RequestCounterFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext sc = filterConfig.getServletContext();
		sc.setAttribute("requestCountMap", requestCountMap);
		
	}

	//filter.doFilter == servlet.service
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// request 객체를 이용하여 요청된 URI 정보를 카운팅
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		
		logger.debug("request uri : {}", uri );
		
		//requestCounterMap에 uri에 해당 키가 있을 수도 있고, 없을 수도 있다. 최초요청이면 없고 아님 있고
		
		//최초요청
		if(requestCountMap.get(uri) == null) {
			requestCountMap.put(uri, 1);
		}
		
		//최초요청이 아닌 경우 - 과거에 이미 요청이 된 적 있는 경우
		else {
			int requestCount = requestCountMap.get(uri);
			requestCountMap.put(uri, requestCount+1);
		}
		
		/* 초기화 메소드를 해당 로직으로 이관(init0)
		 * ServletContext sc = req.getServletContext();
		 * sc.setAttribute("requestCountMap", requestCountMap);
		 */
		
		//다른 필터가 있을 경우 필터로, 필터가 없을 경우 해당 요청을 처리하는 servlet, jsp(jspServlet이 처리함)로 요청 위임
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
