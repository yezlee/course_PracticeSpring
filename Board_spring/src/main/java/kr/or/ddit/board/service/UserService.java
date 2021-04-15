package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.UserVo;
import kr.or.ddit.board.repository.UserDao;
import kr.or.ddit.board.repository.UserDaoI;

@Service("userService")
public class UserService implements UserServiceI{

	@Resource(name = "userDao")
	private UserDaoI dao = new UserDao();
	
	@Override
	public UserVo selectUser(String userid) {
		// TODO Auto-generated method stub
		return dao.selectUser(userid);
	}

}
