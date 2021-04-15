package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.repository.BoardDao;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.PageVo;
import kr.or.ddit.board.model.PostVo;

@Service("boardService")
public class BoardService implements BoardServiceI{

	@Resource(name="boardDao")
	private BoardDao boardDao;
	
	@Override
	public List<BoardVo> selectAllBoard() {
		
		return boardDao.selectAllBoard();
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return boardDao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		return boardDao.updateBoard(boardVo);
	}

	@Override
	public Map<String, Object> selectPostListPagingMap(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PostVo> postList = boardDao.selectPostList(pageVo);
		int postCnt = boardDao.selectPostListCnt(pageVo);
		
		map.put("postList", postList);
		map.put("postCnt", postCnt);
		
		return map;
	}

	@Override
	public PostVo selectPostDetailPage(PostVo postVo) {
		return boardDao.selectPostDetailPage(postVo);
	}

	@Override
	public int insertPost(PostVo postVo) {
		return boardDao.insertPost(postVo);
	}

}
