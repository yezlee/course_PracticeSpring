package kr.or.ddit.board.db.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.db.vo.BoardVo;
import kr.or.ddit.board.db.vo.PageVo;
import kr.or.ddit.board.db.vo.PostVo;
import kr.or.ddit.board.db.MybatisUtil;

public class BoardDao implements BoardDaoI{

	@Override
	public List<BoardVo> selectAllBoard() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectAllBoard");
		
		sqlSession.close();
		
		return boardList;
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("board.insertBoard", boardVo);
		
		if(insertCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("board.updateBoard", boardVo);
		
		if(updateCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return updateCnt;
	}

	@Override
	public List<PostVo> selectPostList(PageVo pageVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<PostVo> postList = sqlSession.selectList("post.selectPostList", pageVo);
		
		sqlSession.close();
		return postList;
	}

	@Override
	public int selectPostListCnt(PageVo pageVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int postCnt = sqlSession.selectOne("post.selectPostListCnt", pageVo);
		
		sqlSession.close();
		return postCnt;
	}

	@Override
	public PostVo selectPostDetailPage(PostVo postVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		PostVo postVoOne = sqlSession.selectOne("post.selectPostDetailPage", postVo);
		
		sqlSession.close();
		
		return postVoOne;
	}

	@Override
	public int insertPost(PostVo postVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int insertCnt = sqlSession.insert("post.insertPost", postVo);
		
		sqlSession.close();
		return insertCnt;
	}
	
	

	
}
