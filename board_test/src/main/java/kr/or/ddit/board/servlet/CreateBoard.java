package kr.or.ddit.board.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.db.service.BoardService;
import kr.or.ddit.board.db.service.BoardServiceI;
import kr.or.ddit.board.db.vo.BoardVo;


@WebServlet("/createBoard.do")
public class CreateBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String boardName = request.getParameter("boardName");
		String actCheck = request.getParameter("actCheck");
		
		int flag = 0;
		if(actCheck.equals("y")) {
			flag = 1;
		}
		
		//이게 set인거야. set의 역할. 3개의 파라미터가 set해서 그 vo한테 보내버리는거지
		BoardVo boardVo = new BoardVo(flag, flag, boardName);
		
		
		BoardServiceI service = new BoardService();
		 
		int insertCnt = 0;
		
		try {
			insertCnt = service.insertBoard(boardVo);
		} catch (Exception e) {
			insertCnt = 0;
		}
		//만약에 성공하면 어디페이지로가고 실패하면 저기 페이지로 간다 하는 로직을
		//catch문 안에 적어주면 된다. 
		//지금 여기선 실패해도 성공해도 똑같은 페이지에 머무니까 사실상 insertCnt가 필요하진 않다.
		
		response.sendRedirect(request.getContextPath() + "/boardController.do");
		
		
		
		
	}

}
