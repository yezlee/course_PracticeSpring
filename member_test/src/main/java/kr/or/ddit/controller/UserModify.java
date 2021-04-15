package kr.or.ddit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import kr.or.ddit.util.depository.FileUtil;
import kr.or.ddit.db.service.UserService;
import kr.or.ddit.db.service.UserServiceI;
import kr.or.ddit.db.vo.UserVo;

@MultipartConfig
@WebServlet("/userModify.do")
public class UserModify extends HttpServlet{

	private static final Logger logger = LoggerFactory.getLogger(UserModify.class);
	
	private UserServiceI userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		UserVo user = userService.selectUser(userid);
		req.setAttribute("user", user);
		
		req.getRequestDispatcher("/user/userModify.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터를 읽기전에 해줘야함. 그래서 doxxx 최상단에 해야지
		//servlet의 doPost 메소드 마다 실행 필요 ==> 나중에 필터라는 개념을 배울건데
		//Filter - 객체임. 여기에 코드를 한번만 입력하면 쓸수있음
		
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		String usernm = req.getParameter("userNm");
		String pass = req.getParameter("pass");
		String reg_dt_string = req.getParameter("reg_dt");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date reg_dt = null;
				
		try {
			reg_dt = sdf.parse(reg_dt_string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String alias = req.getParameter("userAlias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		
		
		
		/*
		 	<파일 수정시 체크사항>
			사용자가 사진을 새롭게 전송을 했는지 여부 체크	
			1. 사진을 안보낸 경우 ==> filename, realfilename 기존 값으로 유지
			2. 사진을 보낸 경우 ==> 업로드시 생성된 filename, realfilename 으로 변경
 
		 */
		
		Part profile = req.getPart("profile");
		
		logger.debug("profile11111 : {}", profile);
		
		String filename="";
		String realfilename="";
		
		if(profile.getSize()>0) {
			
			//1. 전송한 파일 이름(realFile)
			filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
			
			//2. 파일 확장자
			String fileExtension = FileUtil.getFileExtension(filename);

			//3. 서버에 저장할 파일이름(realFileName)
			//brown 이거나 brown.png 일때 만약에 .이 없는 경우면 fileExtension여기에서 미리 .붙인걸 리턴해줘. 그래서 이제 뒤에 +fileExtension 만 해줌됨
			realfilename = UUID.randomUUID().toString() + fileExtension;
			
			//4. 서버에 지정된 공간에 저장
			profile.write("d:\\upload\\" + realfilename);
			
		}else {
			UserVo user = userService.selectUser(userid);
			filename = user.getFilename();
			realfilename = user.getRealfilename();
		}
		
		if(filename == null) {
			filename = "";
		}
		
		if(realfilename == null) {
			realfilename = "";
		}
		
		
		
		UserVo userVo = new UserVo(userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode, filename, realfilename);
		
		int modifyUserCnt = userService.modifyUser(userVo);
		
		if(modifyUserCnt==1) {
			/*
			  req.getRequestDispatcher("/userModify").forward(req, resp);
			  이렇게는 불가능해
			  그래서 그냥 doget을 호출하면 되지(얘를 else로 보내버림...흠???)
			  이렇게 하거나 리다이렉트로 보냄 됨 
			*/
			resp.sendRedirect(req.getContextPath() + "/userController.do?userid=" + userid);
			
		}else {
			doGet(req,resp);
			
		}
	
		
		
		
		
	}
	
}
