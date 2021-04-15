package kr.or.ddit.board.db.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.db.dao.BoardDao;
import kr.or.ddit.board.db.dao.BoardDaoI;
import kr.or.ddit.board.db.vo.BoardVo;
import kr.or.ddit.board.db.vo.PageVo;
import kr.or.ddit.board.db.vo.PostVo;

public class BoardService implements BoardServiceI{

	private BoardDaoI dao = new BoardDao();
	
	@Override
	public List<BoardVo> selectAllBoard() {
		
		return dao.selectAllBoard();
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public Map<String, Object> selectPostListPagingMap(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PostVo> postList = dao.selectPostList(pageVo);
		int postCnt = dao.selectPostListCnt(pageVo);
		
		map.put("postList", postList);
		map.put("postCnt", postCnt);
		
		return map;
	}

	@Override
	public PostVo selectPostDetailPage(PostVo postVo) {
		return dao.selectPostDetailPage(postVo);
	}

	@Override
	public int insertPost(PostVo postVo) {
		return dao.insertPost(postVo);
	}



}
