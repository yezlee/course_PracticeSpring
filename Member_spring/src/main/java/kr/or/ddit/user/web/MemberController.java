package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("member")
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	@Resource(name = "userService")
	private UserService userService;
	
	
	@RequestMapping("user")
	public String allUser(Model model, String userid) {
		userService.selectUser(userid);
		model.addAttribute("user", userService.selectUser(userid));
		return "/user/userDetail";
	}
	
	
	@RequestMapping(path = "userModify", method = RequestMethod.GET)
	public String modifyUserView(Model model, String userid) {
		userService.selectUser(userid);
		model.addAttribute("user", userService.selectUser(userid));
		return "/user/userModify";
	}
	
	
	@RequestMapping(path = "userModify", method = RequestMethod.POST)
	public String actualModifyUser(Model model, UserVo userVo, MultipartFile profile) {
		
		
		UserVo vo = userService.selectUser(userVo.getUserid());
		
		if(userVo.getFilename() == null) {
			userVo.setFilename(vo.getFilename());
			
			if(userVo.getFilename() == null) {
				userVo.setFilename("");
			}
		}
		
		
		if(userVo.getRealfilename() == null) {
			userVo.setRealfilename(vo.getRealfilename());

			if(userVo.getRealfilename() == null) {
				userVo.setRealfilename("");
			}
			
		}
		
		
		if("".equals(profile.getOriginalFilename())) {
			
		}else {
			
			//이거 두줄만 원래 있던 코드인데 
			//수정안하면 즉 널값들어오면 원래 있던 파일 유지하게 하려고 
			//위에 조건문들 추가한거
			userVo.setFilename(profile.getOriginalFilename());
			userVo.setRealfilename(profile.getOriginalFilename());
			
			
			try {
				profile.transferTo(new File(profile.getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
		userService.modifyUser(userVo);
		
		return "redirect:/login/pagingUser";
	}
	
	
	@RequestMapping(path = "userDelete", method = RequestMethod.POST)
	public String deleteUser(String userid) {
		userService.deleteUser(userid);	
		return "redirect:/login/pagingUser";
	}
	
	
	@RequestMapping("userRegistView")
	public String registUserView() {
		return "/user/userRegist";
	}
	
	@RequestMapping(path = "userRegist" , method = RequestMethod.POST)
	public String registUser(@Valid UserVo userVo, BindingResult result, MultipartFile profile, String userid, RedirectAttributes ra){
		
		if(result.hasErrors()) {
			return "/user/userRegist";
		}
		
		userVo.setFilename(profile.getOriginalFilename());
		userVo.setRealfilename(profile.getOriginalFilename());
		
		try {
			profile.transferTo(new File( profile.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		userService.insertUser(userVo);
		
		
		//return "redirect:/member/user?userid=" + userid;
		
		ra.addAttribute("userid", userid);
		return "redirect:/member/user";
	}
	

	
	// localhost/user/profile
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		
		
		resp.setContentType("image");
		
		UserVo userVo = userService.selectUser(userid);
		String path="";
		
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}else {
			path = userVo.getRealfilename();
		}
		
		logger.debug("path : {}" , path);
		
		
		
		FileInputStream fis;
		try {
			
			fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
