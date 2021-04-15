package kr.or.ddit.db.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.db.vo.PageVo;
import kr.or.ddit.db.vo.UserVo;

public interface UserServiceI {
	//전체 사용자 정보 조회
	List<UserVo> selectAllUsers();
		
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);

	//user 페이징 처리 후 조회
	/* List<UserVo> selectPagingUser(PageVo vo); */
	Map<String, Object> selectPagingUser(PageVo vo);
	
	//사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	
	//사용자등록
	int insertUser(UserVo userVo);

	//사용자삭제
	int deleteUser(String userid);
	
	
	//검색 + 검색카운트
	Map<String, Object> searchPagingUser(Map<String, Object> map);
	
		
	
	
}
