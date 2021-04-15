package kr.or.ddit.user.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("login")
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping(path = "view", method = RequestMethod.GET)
	public String view() {
		logger.debug("loginController.view() 111111111");
		return "/user/login";
	}
	
	@RequestMapping(path = "process", method = RequestMethod.POST)
	public String process(UserVo userVo, HttpSession session, RedirectAttributes ra) {
		logger.debug("userVo 22222222 : {}", userVo);
		
		UserVo dbUser = userService.selectUser(userVo.getUserid());
		
		if(dbUser!=null && userVo.getPass().equals(dbUser.getPass())) {
			session.setAttribute("S_USER", dbUser);
			return "redirect:/login/pagingUser";
		}else {
			return "redirect:/login/view";
		}
	}
	
	
	@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo, Model model) {
		
//		Map<String, Object> map = userService.selectPagingUser(pageVo);
//		List<UserVo> userList = (List<UserVo>)map.get("userList");
//		int userCnt = (int)map.get("userCnt");
//		logger.debug("userCnt : {}" ,userCnt);
		
		logger.debug("==============pageVo.getPageSize() : {}", pageVo.getPageSize());
		
		//userService안에 - 서비스에서 이미 키값이랑 데이터를 put해줘서 map을 리턴했으니까 
		//selectPagingUser이거 자체가 맵 형식이야
		//모델한테 속성들을 add해주면 이게 바로 위에 서비스에서 map한테 put한 것들이 통으로 add가 된 것.
		//그럼 리턴해준 memberList.jsp에서 맵에 넣어준 킷값들을 ${} EL표현식을 이용해서 그 속성을 꺼내올 수가 있는 것이다. 
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		return "/user/memberList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
