package kr.or.ddit.board.db.service;

import kr.or.ddit.board.db.vo.UserVo;
import kr.or.ddit.board.db.dao.UserDao;
import kr.or.ddit.board.db.dao.UserDaoI;

public class UserService implements UserServiceI{

	private UserDaoI dao = new UserDao();
	
	@Override
	public UserVo selectUser(String userid) {
		// TODO Auto-generated method stub
		return dao.selectUser(userid);
	}

}
