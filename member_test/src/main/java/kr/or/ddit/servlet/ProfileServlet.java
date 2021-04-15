package kr.or.ddit.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.service.UserService;
import kr.or.ddit.db.service.UserServiceI;
import kr.or.ddit.db.vo.UserVo;

@WebServlet("/profileServlet.do")
public class ProfileServlet extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);
	
	private UserServiceI userService = new UserService();
	//유저서비스 객체를 생성한거지.
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//컨텐트타입을 알면 셋해주는게 좋지. 근데 모르면 안해도됨. 요즘에 똑똑해서 알아서 잡아줌. 
		resp.setContentType("image");
		
		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp 객체의 outputStream으로 응답 생성
		String userid = req.getParameter("userid");
		
		UserVo userVo = userService.selectUser(userid);
		String path="";
		
		if(userVo.getRealfilename() == null) {
			path = getServletContext().getRealPath("/image/unknown.png");
		}else {
			path = "d:\\upload\\" + userVo.getRealfilename();
		}
		
		logger.debug("path : {}" , path);
		
		FileInputStream fis = new FileInputStream(path);
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		
		while(fis.read(buff) != -1) {
			sos.write(buff);
		}
		
		fis.close();
		sos.close();
	}

}
