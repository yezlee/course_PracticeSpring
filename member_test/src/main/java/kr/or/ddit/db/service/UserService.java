package kr.or.ddit.db.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.vo.PageVo;
import kr.or.ddit.db.vo.UserVo;
import kr.or.ddit.db.dao.UserDao;
import kr.or.ddit.db.dao.UserDaoI;

public class UserService implements UserServiceI {
	
	private UserDaoI dao = new UserDao();
	
	@Override
	public List<UserVo> selectAllUsers() {
		// TODO Auto-generated method stub
		return dao.selectAllUsers();
	}

	@Override
	public UserVo selectUser(String userid) {
		// TODO Auto-generated method stub
		return dao.selectUser(userid);
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserVo> userList = dao.selectPagingUser(vo);
		int userCnt = dao.selectAllUserCnt();
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		return dao.modifyUser(userVo);
	}

	@Override
	public int insertUser(UserVo userVo) {
		return dao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String userid) {
		return dao.deleteUser(userid);
	}

	@Override
	public Map<String, Object> searchPagingUser(Map<String, Object> map) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		List<UserVo> userList = dao.searchPagingUser(map);
		
		Map<String, Object> cntMap = new HashMap<String, Object>();
		
		cntMap.put("field", map.get("field"));
		cntMap.put("data", map.get("data"));
		
		int userCnt = dao.searchPagingCnt(cntMap);

		
		paramMap.put("userList", userList);
		paramMap.put("userCnt", userCnt);
		
		
		return paramMap;
	}
	
}
