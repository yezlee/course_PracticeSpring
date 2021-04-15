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


@WebServlet("/boardController.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BoardServiceI service = new BoardService();
		
		List<BoardVo> boardList = service.selectAllBoard();
		
		logger.debug("boardList 00000 : {}" , boardList);
		
		request.setAttribute("boardList", boardList);
		
		request.getRequestDispatcher("/main.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
