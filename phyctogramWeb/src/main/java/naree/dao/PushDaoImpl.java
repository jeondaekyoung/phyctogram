package naree.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.mapper.PushMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class PushDaoImpl implements PushDao {
	
	@Override
	public List<String> selectBoxList() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<String> result = null;
		try {
			PushMapper pushMapper = sqlSession.getMapper(PushMapper.class);
			result = pushMapper.selectBoxList();
		} finally {
			sqlSession.close();
		}
		return result;
	}
}
