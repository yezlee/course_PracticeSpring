package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.PageVo;
import kr.or.ddit.board.model.PostVo;

public interface BoardServiceI {
	
	//전체 사용자 정보 조회
	List<BoardVo> selectAllBoard();
	
	//게시판 생성
	int insertBoard(BoardVo boardVo);
	
	//게시판 수정
	int updateBoard(BoardVo boardVo);
	
	//게시판 글 페이징 처리 + 계층쿼리해서 셀렉한 리스트
	//게시판 글 페이지네이션 하기 위해서 카운트(글이 몇개인지 알아야 페이징을 하니까)
	//위에거 두개를 합쳐서 처리할 맵
	Map<String , Object> selectPostListPagingMap(PageVo pageVo);
	
	//게시판에서 글 클릭했을때 상세페이지
	PostVo selectPostDetailPage(PostVo postVo);
	
	//게시판 글 등록
	int insertPost(PostVo postVo);
	
}
