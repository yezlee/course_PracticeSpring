package kr.or.ddit.board.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.db.service.BoardService;
import kr.or.ddit.board.db.service.BoardServiceI;
import kr.or.ddit.board.db.vo.BoardVo;

/**
 * Servlet implementation class ModifyBoard
 */
@WebServlet("/modifyBoard.do")
public class ModifyBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String actCheck = request.getParameter("actCheck");
		
		int flag = 0;
		if(actCheck.equals("y")) {
			flag = 1;
		}
		
		BoardServiceI service = new BoardService();
		
		//BoardVo에 set해주는거
		BoardVo boardVo = new BoardVo(boardNo, flag, "anything");

		service.updateBoard(boardVo);
		
		response.sendRedirect(request.getContextPath() + "/boardController.do");
		
	}
}
