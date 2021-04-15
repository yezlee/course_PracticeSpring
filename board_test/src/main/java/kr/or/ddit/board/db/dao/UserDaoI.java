package kr.or.ddit.board.db.dao;

import kr.or.ddit.board.db.vo.UserVo;

public interface UserDaoI {
	
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
}
