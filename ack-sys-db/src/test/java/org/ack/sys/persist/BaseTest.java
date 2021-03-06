package org.ack.sys.persist;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class BaseTest {
	protected static SqlSessionFactory sqlSessionFactory;
	protected static SqlSession sqlSession;
	static {
		String resource = "config/mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	public void commit() {
		sqlSession.commit();
	}

	public void close() {
		sqlSession.close();
	}

}
