package kr.or.ddit.board.db.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.db.vo.UserVo;
import kr.or.ddit.board.db.MybatisUtil;

public class UserDao implements UserDaoI{

	@Override
	public UserVo selectUser(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		UserVo user = sqlSession.selectOne("users.selectUser", userid);
		sqlSession.close();
		
		return user;
	}

}
