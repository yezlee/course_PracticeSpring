package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.db.service.BoardService;
import kr.or.ddit.board.db.service.BoardServiceI;
import kr.or.ddit.board.db.vo.BoardVo;
import kr.or.ddit.board.db.vo.PostVo;


@WebServlet("/postController.do")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		int bd_no = Integer.parseInt(request.getParameter("bd_no"));
		String writer = request.getParameter("writer");
		
		//위에서 받은 변수들을 아래 파라미터로 보내는거
		//이게 세터야
		PostVo postVo = new PostVo(bd_no, post_no, writer, writer, writer, null, post_no, post_no, bd_no, writer);
		
		BoardServiceI service = new BoardService();
		
		
		//그걸 파라미터로 담아서 서비스 메소드를 호출한다.
		PostVo postVoOne = service.selectPostDetailPage(postVo);
		request.setAttribute("postVo", postVoOne);
		
		
		/* 보드리스트(left_navi)를 불러오기위해서 보드컨트롤러에 있던거 복붙 */
		List<BoardVo> boardList = service.selectAllBoard();
		logger.debug("boardList 00000 : {}" , boardList);
		request.setAttribute("boardList", boardList);
		
		
		
		
		request.getRequestDispatcher("/jsp/postDetailPage.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
