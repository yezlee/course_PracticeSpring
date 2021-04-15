package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import kr.or.ddit.board.db.vo.PageVo;
import kr.or.ddit.board.db.vo.PostVo;


@WebServlet("/boardListingPageController.do")
public class BoardListingPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardListingPageController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String boardNoStr = request.getParameter("boardNo");
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		
		int page = pageStr == null ?  1 : Integer.parseInt(pageStr);
		int pageSize = pageSizeStr==null ? 10:Integer.parseInt(pageSizeStr); 
		
		int boardNo = 0;
		
		if(boardNoStr!=null) {
			boardNo = Integer.parseInt(boardNoStr);
		}
		
		BoardServiceI service = new BoardService();

		PageVo pageVo = new PageVo(page, pageSize, boardNo);
		
		
		//service에서 불러온 selectPostListPagingMap 이게 map타입이야(보드서비스에서 맵타입으로 만듦)
		//그래서 형변환 안해줘도 에러 안뜨고 그걸 map에다가 넣고
		Map<String , Object> map = service.selectPostListPagingMap(pageVo);
		
		//맵으로 넣었던 "postList"를 다시 꺼내서 원래 dao에서 만든 List<PostVo>로 형변환 해준뒤 
		List<PostVo> postList = (List<PostVo>)map.get("postList");
		int postCnt = (int)map.get("postCnt");
		
		int pagination = (int)Math.ceil((double) postCnt/pageSize);
		
		//리케스트 객체한테 보내준거지, jsp에서 쓰라고
		request.setAttribute("postList", postList);
		request.setAttribute("pagination", pagination);
		request.setAttribute("pageVo", pageVo);
		
		
		logger.debug("postList 111111111111: {}", postList);
		logger.debug("pagination 2222222222: {}", pagination);
		logger.debug("pageVo 33333333333333: {}", pageVo);
		
		
		/* 보드리스트(left_navi)를 불러오기위해서 보드컨트롤러에 있던거 복붙 */
		List<BoardVo> boardList = service.selectAllBoard();
		logger.debug("boardList 00000 : {}" , boardList);
		request.setAttribute("boardList", boardList);
		
		
		
		request.getRequestDispatcher("/jsp/boardListingPage.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
