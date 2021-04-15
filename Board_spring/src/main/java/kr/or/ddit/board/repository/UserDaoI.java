package kr.or.ddit.board.repository;

import kr.or.ddit.board.model.UserVo;

public interface UserDaoI {
	
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
}
