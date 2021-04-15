package kr.or.ddit.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.service.UserService;
import kr.or.ddit.db.service.UserServiceI;
import kr.or.ddit.db.vo.PageVo;
import kr.or.ddit.db.vo.UserVo;


@WebServlet("/searchPagingUser.do")
public class SearchPagingUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SearchPagingUser.class);
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pageParam = request.getParameter("page");
		String pageSizeParam = request.getParameter("pageSize");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		

		
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pageSize = pageSizeParam == null ? 5 : Integer.parseInt(pageSizeParam);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PageVo vo = new PageVo(page,pageSize);
		
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		
		//카테고리에 따른 조건문 
		if(type.equals("i")) {
			paramMap.put("field", "userid");
			paramMap.put("data", keyword);
		}else if(type.equals("n")) {
			paramMap.put("field", "usernm");
			paramMap.put("data", keyword);
		}else if(type.equals("a")) {
			paramMap.put("field", "alias");
			paramMap.put("data", keyword);
		}else {
			paramMap.put("field", "userid");
			paramMap.put("data", "a");
		}
		
		UserServiceI service = new UserService();
		
		Map<String, Object> map = service.selectPagingUser(vo); 
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
								
		int userCnt = (int)map.get("userCnt");
		int pagination = (int)Math.ceil((double) userCnt / pageSize);

		logger.debug("pagination12 : {}", pagination);	
				
		request.setAttribute("userList", userList);
		request.setAttribute("pagination", pagination);
		request.setAttribute("pageVo", vo); //내가 어디페이지에 있는지 알기 위해 추가
		
		
		
		request.getRequestDispatcher("/memberList.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
