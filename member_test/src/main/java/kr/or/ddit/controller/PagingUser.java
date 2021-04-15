package kr.or.ddit.controller;

import java.io.IOException;
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

@WebServlet("/pagingUser.do")
public class PagingUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PagingUser.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pageParam = request.getParameter("page");
		String pageSizeParam = request.getParameter("pageSize");
		
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pageSize = pageSizeParam == null ? 5 : Integer.parseInt(pageSizeParam);
		
		logger.debug("page12121 : {}", page);	
		logger.debug("pageSize : {}", pageSize);	
		
		UserServiceI service = new UserService();
		PageVo vo = new PageVo(page,pageSize);
		
		Map<String, Object> map = service.selectPagingUser(vo); 
		//맵을 쓰는 이유 - 값 두개 넣어주려고
		// : map.put("userList", userList);
		//   map.put("userCnt", userCnt);
		//==> 서비스에서 값 두개 넣어준거
		//서비스에선 맵객체 만들어서 각자 다오에서 갖고온 값을 맵에 넣어줌
		
		
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
