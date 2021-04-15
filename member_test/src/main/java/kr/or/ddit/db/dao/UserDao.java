package kr.or.ddit.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.vo.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.db.vo.UserVo;

public class UserDao implements UserDaoI{

	/*
	 	SqlSession sqlsession = MybatisUtil.getSqlSession();
	 	트랜잭션이 다르니까 insert랑 delete랑 트랜잭션이 다르기때문에 할때마다, 사용자가 다르니까
	 	새롭게 만들어줘야해
	 	위에 여기에 써서 전역변수로 쓰면 안됨
	 */
	
	
	@Override
	public List<UserVo> selectAllUsers() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		//select : 리턴되는 값의 복수 유무를 판단
		//			1. single : 일반객체를 반환(UserVo) selectOne()
		//			2. multiple : List<UserVo> selectList()
		//insert, update, delete : insert, update, delete(메소드 이름과 동일하게 불러온다.)
		
		//메소드 이름과 실행할 sql id를 동일하게 맞추자. 그럼 나중에 유지보수할때 편함(다른 개발자의 가독성을 위해서)
		
		List<UserVo> userList = sqlSession.selectList("users.selectAllUser"); //메소드 이름과 동일하게. 다만 namespace는 붙여줘야지. (users)
		
		//마이바티스는 conn stmt - 이런거 알아서 SqlSession 여기서 다 해줘 그래서 안써줘도 돼. 바로 사용한 자원 반환 고		
		//사용한 자원 반환
		sqlSession.close();
		
		return userList;
	}

	@Override
	public UserVo selectUser(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		UserVo user = sqlSession.selectOne("users.selectUser", userid);
		sqlSession.close();
		
		return user;
	}
	
	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<UserVo> userList = sqlSession.selectList("users.selectPagingUser", vo);
		sqlSession.close();

		return userList;
	}

	@Override
	public int selectAllUserCnt() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int userCnt = sqlSession.selectOne("users.selectAllUserCnt");
		//파라미터값 필요없음. select cnt(*) from emp; 
		
		sqlSession.close();
		return userCnt;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("users.modifyUser", userVo);
		//업데이트한 행의 수를 반납해줘 ==> 결과적으로 마이바티스에서도 jdbc를 사용하고 있는거야
		
		//무조건 업데이트한 사항은 1개니까 조건문을 걸어서 하자
		if(updateCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		//사실 클로즈하면 업뎃 안된건 저절로 롤백되서 else에 쓴건 필요는 없지만.
		
		return updateCnt;
	}

	@Override
	public int insertUser(UserVo userVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("users.insertUser", userVo);
		
		if(insertCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return insertCnt;
		
		
				
	}

	@Override
	public int deleteUser(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("users.deleteUser", userid);
		
		if(deleteCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return deleteCnt;
	}

	@Override
	public List<UserVo> searchPagingUser(Map<String, Object> map) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<UserVo> userList = sqlSession.selectList("users.searchPagingUser", map);
		sqlSession.close();
		
		return userList;
	}

	@Override
	public int searchPagingCnt(Map<String, Object> map) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int userCnt = sqlSession.selectOne("users.searchPagingCnt", map);
		sqlSession.close();
		
		return userCnt;
	}
	
	
	
	
}
