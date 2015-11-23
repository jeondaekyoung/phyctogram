package naree.util.factory;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectionFactory {


	private static ConnectionFactory instance;
	private static SqlSessionFactory sqlSessionFactory;
	private final String resource = "mybatis-config.xml";
	
	private ConnectionFactory(){
		
	}
	
	public static ConnectionFactory getInstance(){
		if(instance == null){
			instance = new ConnectionFactory();
		}
		return instance;
	}
	
	public SqlSession getSqlSession(){
		Reader reader = null;
		if(sqlSessionFactory == null){
			try{
				reader = Resources.getResourceAsReader(resource);
			} catch(IOException e){
				e.printStackTrace();
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			return sqlSessionFactory.openSession();
		}
		return sqlSessionFactory.openSession();
	}
}
