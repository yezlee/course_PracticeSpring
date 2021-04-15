package kr.or.ddit.user.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.user.service.UserService;


@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml",
"classpath:/kr/or/ddit/config/spring/root-context.xml", "classpath:/kr/or/ddit/config/spring/datasource-context.xml"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

	@Autowired 
	private WebApplicationContext context;
	protected MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Resource(name="userService")
	private UserService userService;

	@Test
	public void viewTest() throws Exception {
		//perform() : 무언가를 실행을 한다
		//status().isOk() : 200
		//view().name("hello") : viewname 반환
		//model().attributeExists("userVo") : 들어있는 속성 반환
		MvcResult mvcResult = mockMvc.perform(get("/login/view"))
								.andExpect(status().isOk())
							    .andExpect(view().name("/user/login"))
							    .andDo(print())
							    .andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		assertEquals("/user/login", mav.getViewName());
	}
	
	
	@Test
	public void processSucessTest() throws Exception {
		mockMvc.perform(post("/login/process")
							.param("userid", "brown")
							.param("pass", "brownPass"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/login/pagingUser"))
				.andDo(print());
	}
	
	
	@Test
	public void processFailTest() throws Exception {
		mockMvc.perform(post("/login/process")
							.param("userid", "brown")
							.param("pass", "123123Pass"))
				.andExpect(view().name("redirect:/login/view"))
				.andDo(print());
	}
	
	
	
	
	
	
}
