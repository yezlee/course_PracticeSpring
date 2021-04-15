package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.model.PageVo;
import kr.or.ddit.board.model.PostVo;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.UserVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.UserService;

@RequestMapping("board")
@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);	
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	
	
	@RequestMapping("view")
	public String view() {
		return "/user/login";
	}
	
	@RequestMapping(path="login", method = RequestMethod.POST)
	public String loginController(String userid, String pass, HttpSession session, Model model) {
		
		UserVo userVo = userService.selectUser(userid);
	
		if(userVo!=null && pass.equals(userVo.getPass())) {
			session.setAttribute("S_USER", userVo);
			logger.debug("S_USER, userVo : {}" + userVo);
			return "redirect:/board/main";
			
		}else {
			return "redirect:/board/view";
		}
	}
	
	
	@RequestMapping("main")
	public String goToMainPage(Model model) {
		List<BoardVo> boardList = boardService.selectAllBoard();
		model.addAttribute("boardList", boardList);
		return "/user/mainAfterLogin";
	}
	
	
	@RequestMapping("boardList")
	public String boardController(Model model){
		List<BoardVo> boardList = boardService.selectAllBoard();
		model.addAttribute("boardList", boardList);
		return "/user/mainMakingBoard";
	}
	
	
	@RequestMapping(path ="createBoard", method = RequestMethod.POST)
	public String createBoardController(String boardName, String actCheck) {
		
		int flag = 0;
		if(actCheck.equals("y")) {
			flag=1;
		}
		
		BoardVo boardVo = new BoardVo(flag, flag, boardName);
		
		boardService.insertBoard(boardVo);
		
		
//		int insertCnt = 0;
//		
//		try {
//			insertCnt = service.insertBoard(boardVo);
//		} catch (Exception e) {
//			insertCnt = 0;
//		}
		//만약에 성공하면 어디페이지로가고 실패하면 저기 페이지로 간다 하는 로직을
		//catch문 안에 적어주면 된다. 
		//지금 여기선 실패해도 성공해도 똑같은 페이지에 머무니까 사실상 insertCnt가 필요하진 않다.
		
		
		return "redirect:/board/boardList";
	}
	
	
	@RequestMapping(path = "modifyBoard", method = RequestMethod.POST)
	public String modifyBoardController(int boardNo, String actCheck) {
		
		int flag = 0;
		if(actCheck.equals("y")) {
			flag = 1;
		}
		
		//BoardVo에 set해주는거
		BoardVo boardVo = new BoardVo(boardNo, flag, "anything");
		boardService.updateBoard(boardVo);
		
		return "redirect:/board/boardList";
	}
	
	
	
	
	
	//페이징 처리하는 방법이 이렇게 컨트롤러에서 모든 로직을 다 써주는게 있고
	//Member_string에서 했던것처럼 서비스, vo 등등 다 나눠서 로직을 미리 써놓고 컨트롤러에선 그냥 add만 하면 되게끔 하는 방법도 있다.
	@RequestMapping("boardListingPage")
	public String boardListingPageController(Model model, int boardNo, String pageStr, String pageSizeStr) {
		
		
		int page = pageStr == null ?  1 : Integer.parseInt(pageStr);
		int pageSize = pageSizeStr==null ? 10:Integer.parseInt(pageSizeStr); 
		

		PageVo pageVo = new PageVo(page, pageSize, boardNo);
		
		
		//service에서 불러온 selectPostListPagingMap 이게 map타입이야(보드서비스에서 맵타입으로 만듦)
		//그래서 형변환 안해줘도 에러 안뜨고 그걸 map에다가 넣고
		Map<String , Object> map = boardService.selectPostListPagingMap(pageVo);
		
		//맵으로 넣었던 "postList"를 다시 꺼내서 원래 dao에서 만든 List<PostVo>로 형변환 해준뒤 
		List<PostVo> postList = (List<PostVo>)map.get("postList");
		int postCnt = (int)map.get("postCnt");
		
		int pagination = (int)Math.ceil((double) postCnt/pageSize);
		
		//old : 리케스트 객체한테 보내준거지, jsp에서 쓰라고
		//new : 스프링으로 바꾸면서 모델로 바꿈(리케스트는 변경이 불가능한데 모델은 가능해서?) 
		model.addAttribute("postList", postList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		
		
		logger.debug("postList 111111111111: {}", postList);
		logger.debug("pagination 2222222222: {}", pagination);
		logger.debug("pageVo 33333333333333: {}", pageVo);
		
		
		/* 보드리스트(left_navi)를 불러오기위해서 보드컨트롤러에 있던거 복붙 */
		List<BoardVo> boardList = boardService.selectAllBoard();
		logger.debug("boardList 00000 : {}" , boardList);
		model.addAttribute("boardList", boardList);
		
		return "/jsp/boardListingPage";
	}
	
	
	

	
	
	/////////////////////////post///////////////////////////
	
	
	@RequestMapping("post")
	public String postController(PostVo postVo, Model model) {
		
		//위에서 받은 변수들을 아래 파라미터로 보내는거
		//이게 세터야
//		PostVo postVo = new PostVo(bd_no, post_no, writer, writer, writer, null, post_no, post_no, bd_no, writer);
		//이걸 안해도 된다. 
		//파라미터가 세개만 필요해도 그 세개를 안쓰고 vo를 통으로 파라미터로 써도 됨
		model.addAttribute("postVo", boardService.selectPostDetailPage(postVo));
		
		/* 보드리스트(left_navi)를 불러오기위해서 보드컨트롤러에 있던거 복붙 */
		List<BoardVo> boardList = boardService.selectAllBoard();
		logger.debug("boardList 00000 : {}" , boardList);
		model.addAttribute("boardList", boardList);
		
		return "/jsp/postDetailPage";
	}
	

	
	@RequestMapping("createPostView")
	public String createPostPageController(int boardNo, Model model) {
		model.addAttribute("boardNo", boardNo);
		
		/* 보드리스트(left_navi)를 불러오기위해서 보드컨트롤러에 있던거 복붙 */
		List<BoardVo> boardList = boardService.selectAllBoard();
		logger.debug("boardList 00000 : {}" , boardList);
		model.addAttribute("boardList", boardList);
		
		return "/jsp/createPostPage";
	}

	
	@RequestMapping(path = "createPost", method = RequestMethod.POST)
	public String createPostController(int boardNo, String userId, String title, String editordata, RedirectAttributes ra, Model model) {
		
		PostVo postVo = new PostVo();
		postVo.setBd_no(boardNo);
		postVo.setWriter(userId);
		postVo.setTitle(title);
		postVo.setCont(editordata);
		
		int insertPostCnt = boardService.insertPost(postVo);
		
		if(insertPostCnt==1) {
			ra.addAttribute("boardNo", boardNo);
			return "redirect:/board/boardListingPage";
		}else {
			model.addAttribute("boardNo", boardNo);
			model.addAttribute("userId", userId);
			model.addAttribute("title", title);
			model.addAttribute("editordata", editordata);
			
			return "/jsp/createPostPage";
		}
	}
	
	
	
	@RequestMapping("modifyPost")
	public String modifyPostController(int post_no, int bd_no, String writer, String title, String cont, Model model) {
		
		PostVo postVo = new PostVo();
		postVo.setBd_no(bd_no);
		postVo.setWriter(writer);
		postVo.setTitle(title);
		postVo.setPost_no(post_no);
		postVo.setCont(cont);
		
		
		/* 보드리스트(left_navi)를 불러오기위해서 보드컨트롤러에 있던거 복붙 */
		List<BoardVo> boardList = boardService.selectAllBoard();
		logger.debug("boardList 00000 : {}" , boardList);
		model.addAttribute("boardList", boardList);
		
		return "/jsp/modifyPostPage";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
