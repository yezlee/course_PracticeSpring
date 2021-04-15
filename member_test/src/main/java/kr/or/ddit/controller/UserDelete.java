package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.service.UserService;
import kr.or.ddit.db.service.UserServiceI;

@WebServlet("/userDelete.do")
public class UserDelete extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDelete.class);
	private UserServiceI userService = new UserService();
	
	//삭제할때는 서버에 변화가 있단뜻이지 그래서 포스트 방식으로 해야해
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userid");
		
		int deleteCnt = 0;
		try {
			deleteCnt = userService.deleteUser(userid);
		} catch (Exception e) {
			deleteCnt = -1;
		}
		
		if(deleteCnt == 1) {
			resp.sendRedirect(req.getContextPath() + "/pagingUser.do");
		}else {
			resp.sendRedirect(req.getContextPath() + "/userController.do?userid="+ userid);
		}
	}
	
}
