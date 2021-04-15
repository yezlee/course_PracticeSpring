package kr.or.ddit.board.db.dao;

import java.util.List;

import kr.or.ddit.board.db.vo.BoardVo;
import kr.or.ddit.board.db.vo.PageVo;
import kr.or.ddit.board.db.vo.PostVo;

public interface BoardDaoI {
	
	//전체 게시판 조회
	List<BoardVo> selectAllBoard();
	
	//게시판 생성
	int insertBoard(BoardVo boardVo);
	
	//게시판 수정
	int updateBoard(BoardVo boardVo);
	
	//게시판 글 페이징 처리 + 계층쿼리해서 셀렉한 리스트
	List<PostVo> selectPostList(PageVo pageVo);
	
	//게시판 글 페이지네이션 하기 위해서 카운트(글이 몇개인지 알아야 페이징을 하니까)
	int selectPostListCnt(PageVo pageVo);
	
	//게시판에서 글 클릭했을때 상세페이지
	PostVo selectPostDetailPage(PostVo postVo);
	
	//게시판 글 등록
	int insertPost(PostVo postVo);
	
}
