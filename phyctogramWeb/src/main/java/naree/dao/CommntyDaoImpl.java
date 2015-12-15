package naree.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Commnty;
import naree.db.mapper.CommntyMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class CommntyDaoImpl implements CommntyDao {

	@Override
	public int insertCommnty(Commnty commnty) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.insertCommnty(commnty);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

}
