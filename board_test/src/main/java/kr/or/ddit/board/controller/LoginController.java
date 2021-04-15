package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.db.service.UserService;
import kr.or.ddit.board.db.service.UserServiceI;
import kr.or.ddit.board.db.vo.UserVo;


@WebServlet("/loginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserServiceI userService = new UserService();
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		
		UserVo user = userService.selectUser(userid);
		
		if(user != null && pass.equals(user.getPass())) {
			HttpSession session = request.getSession();
			session.setAttribute("S_USER", user);
			
			logger.debug("S_USER123123 : {}", user );
			
		    RequestDispatcher rd = request.getRequestDispatcher("/boardController.do");
		    rd.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}
}
