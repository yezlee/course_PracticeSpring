package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	
	static SqlSessionFactory sqlSessionFactory;
	
	//class 로딩시 실행되는 블럭
	static {
		try {
			String resource = "kr/or/ddit/config/mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//객체(new해서 만드는거)를 안만들고 하기때문에, static이어야돼. 그래야 다른데서 불러오지. (외부에서 객체없이 불러오기 위해서 static)
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
			
}
