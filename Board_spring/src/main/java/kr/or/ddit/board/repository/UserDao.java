package kr.or.ddit.board.repository;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.UserVo;

@Repository("userDao") 
public class UserDao implements UserDaoI{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	
	@Override
	public UserVo selectUser(String userid) {
		return template.selectOne("users.selectUser", userid);
	}

}
