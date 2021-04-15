package kr.or.ddit.board.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.PageVo;
import kr.or.ddit.board.model.PostVo;


//객체가 생성되면 객체가 저장된 주소를 반환해주는데, 
//private SqlSessionTemplate template;
//이렇게만 쓰면 그냥 앞에 타입만 쓴 형태야. new해서 객체를 생성해주기 전에 모양.
//
//@Resource(name = "sqlSessionTemplate")
//이렇게 해줌으로써 new 써서 만든것처럼 객체를 생성해준 것. 
//이렇게 객체가 생성되면, 객체가 저장된 주소를 반환해주는거야.


@Repository("boardDao")
public class BoardDao implements BoardDaoI{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	
	@Override
	public List<BoardVo> selectAllBoard() {
		List<BoardVo> boardList = template.selectList("board.selectAllBoard");
		return boardList;
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return template.insert("board.insertBoard", boardVo);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		return template.update("board.updateBoard", boardVo);
	}

	@Override
	public List<PostVo> selectPostList(PageVo pageVo) {
		return template.selectList("post.selectPostList", pageVo);
	}

	@Override
	public int selectPostListCnt(PageVo pageVo) {
		return template.selectOne("post.selectPostListCnt", pageVo);
	}

	@Override
	public PostVo selectPostDetailPage(PostVo postVo) {
		return template.selectOne("post.selectPostDetailPage", postVo);
	}

	@Override
	public int insertPost(PostVo postVo) {
		return template.insert("post.insertPost", postVo);
	}
	
	

	
}
