package kr.or.ddit.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.vo.UserVo;

import kr.or.ddit.db.service.UserService;
import kr.or.ddit.db.service.UserServiceI;


@WebServlet("/loginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private UserServiceI userService = new UserService();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		logger.debug("loginController.do.TEST");
		
		String userid = req.getParameter("userid");
		String pass = req.getParameter("pass");
		
		logger.debug("userid : {}", userid);
		
		UserVo user = userService.selectUser(userid);
		
		//로그인성공 ==> service를 통해 데이터베이스에 저장된 값과 일치할 때
		// session에 데이터베이스에서 조회한 사용자 정보(userVo)를 저장
		if(user != null && pass.equals(user.getPass())) {
			HttpSession session = req.getSession();
			session.setAttribute("S_USER", user);
			
		    RequestDispatcher rd = req.getRequestDispatcher("/pagingUser.do");
		    rd.forward(req, resp);
		    
		    //forward 할때는 url경로에 req.getContextPath() 이걸 쓰면 안돼.
		    //root로 / 이렇게 되어있음 상관없는데 /jsp 이렇게 되어있으면
		    //이미 webapp에서 부터 시작하는데 /jsp/jsp 이렇게 될테니까 에러뜸!!!
		}
		//실패하면 널이 들어감. 그래서 널이 아닐때
		
		//로그인실패
		else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
						
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
