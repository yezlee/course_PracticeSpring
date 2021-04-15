package kr.or.ddit.board.servlet;

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


@WebServlet("/createPost.do")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CreatePost.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		request.setAttribute("boardNo", boardNo);
		
		BoardServiceI service = new BoardService(); 
		
		/* 보드리스트(left_navi)를 불러오기위해서 보드컨트롤러에 있던거 복붙 */
		List<BoardVo> boardList = service.selectAllBoard();
		request.setAttribute("boardList", boardList);
		
		logger.debug("boardList 123123132 : {}", boardList);
		
		request.getRequestDispatcher("jsp/createPostPage.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		//vo 생성자를 만들어주는데, 
		//파라미터가 있는경우엔 파라미터값을 set해줘야 하잖아
		//모든 파라미터를 셋해줘야하면 vo에서 오버로딩한 생성자를 만들어놓고 여기 서블릿에서 생성자 만들때, 오버로딩한 애들을 다 적어주면됨. 그 인자수만큼.
		//만약에 파라미터가 없는 애들이면 어차피 값이 안들어가니까 아무값이나 넣어도 되고? -> 이건 수정할때
		//BoardVo boardVo = new BoardVo(boardNo, flag, "anything"); 이렇게 했던거처럼
		//만약에 set해줄게 한개면 그냥 기본 생성자 불러서 거기에 set해주면 되지.
		//셋해줄때 위에서 파라미터로 받은 값을 넣어주고
		/*
		 * PostVo postVo = new PostVo(); 
		 * postVo.setBd_no(boardNo);
		 * 
		 * c
		 */

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String cont = request.getParameter("editordata");
		
		logger.debug("cont : {}", cont);
		
		
		PostVo postVo = new PostVo();
		postVo.setBd_no(boardNo);
		postVo.setWriter(userId);
		postVo.setTitle(title);
		postVo.setCont(cont);
		
		BoardServiceI service = new BoardService();

		int insertPostCnt = 0;
		
		try {
			insertPostCnt = service.insertPost(postVo);
		} catch (Exception e) {
			insertPostCnt = 0;
		}
		
		
		
		if(insertPostCnt==1) {
			response.sendRedirect(request.getContextPath() + "/boardListingPageController.do?boardNo=" + boardNo);
		}else {
			
		}
		
		
		
		
	}
}































