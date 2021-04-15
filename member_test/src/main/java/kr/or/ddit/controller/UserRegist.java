package kr.or.ddit.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.service.UserService;
import kr.or.ddit.db.service.UserServiceI;
import kr.or.ddit.db.vo.UserVo;
import kr.or.ddit.util.depository.FileUtil;

@MultipartConfig
@WebServlet("/userRegist.do")
public class UserRegist extends HttpServlet{

	private static final Logger logger = LoggerFactory.getLogger(UserRegist.class);
	private UserServiceI userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/userRegist.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		logger.debug("userid2222 : {}", userid);
		String usernm = req.getParameter("userNm");
		String pass = req.getParameter("pass");
		
		/*
		 * String reg_dt_string = req.getParameter("reg_dt");
		 * 
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); Date reg_dt =
		 * null;
		 * 
		 * try { reg_dt = sdf.parse(reg_dt_string); } catch (ParseException e) {
		 * e.printStackTrace(); }
		 */
		
		String alias = req.getParameter("userAlias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		
		
		
		//사용자가 profile 사진을 업로드 한경우 - 업로드 했을때만
		//1. 전송한 파일 이름(realFile)
		//2. 파일 확장자
		//3. 서버에 저장할 파일이름(realFileName)
		//4. 서버에 지정된 공간에 저장
		
		Part profile = req.getPart("profile");
		String filename="";
		String realfilename="";
		
		logger.debug("profile11111 : {}", profile);
		
		if(profile.getSize()>0) {
			
			//1. 전송한 파일 이름(realFile)
			filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
			logger.debug("filename11111 : {}", filename);
			
			//2. 파일 확장자
			String fileExtension = FileUtil.getFileExtension(filename);

			//3. 서버에 저장할 파일이름(realFileName)
			//brown 이거나 brown.png 일때 만약에 .이 없는 경우면 fileExtension여기에서 미리 .붙인걸 리턴해줘. 그래서 이제 뒤에 +fileExtension 만 해줌됨
			realfilename = UUID.randomUUID().toString() + fileExtension;
			
			//4. 서버에 지정된 공간에 저장
			profile.write("d:\\upload\\" + realfilename);
			
			
		}
		
		
		
		UserVo userVo = new UserVo(userid, usernm, pass, new Date(), alias, addr1, addr2, zipcode, filename ,realfilename );
		
		int registerUserCnt=0;

		
		//try-catch로 잡아주는 이유
		//userService.insertUser(userVo); 지금 얘를 불러서 registerUserCnt 여기에 넣어주려고 했는데
		//userVo는 널값이야 그러면 널포인터가 뜨니까 exception으로 잡고 registerUserCnt=0;이걸 해줘서
		//아래로 쭉 실행될수있게끔
		try {
			registerUserCnt = userService.insertUser(userVo);
		} catch (Exception e) {
			registerUserCnt=0;
		}
		
		//얘한테 예외처리를 해줘야한다
		//아니면 서비스에 리턴값한테 해주거나
		//어딘가에서 한번은 예외처리해줘야함
		//업데이트도 예외처리 해줘야해 카운트하는거
		
		if(registerUserCnt==1) {
			resp.sendRedirect(req.getContextPath() + "/pagingUser.do");
		}else {
			req.setAttribute("userVo", userVo);
			req.getRequestDispatcher("/user/userRegist.jsp").forward(req, resp);
		}
		
		
	}
	
	
	
}
