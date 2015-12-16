package naree.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Commnty;
import naree.db.domain.SqlCommntyListView;
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

	@Override
	public List<SqlCommntyListView> selectCommntyLatest(int pageCntFirstIndex) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<SqlCommntyListView> result;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.selectCommntyLatest(pageCntFirstIndex);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	@Override
	public List<SqlCommntyListView> selectCommntyPopular(int pageCntFirstIndex) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<SqlCommntyListView> result;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.selectCommntyPopular(pageCntFirstIndex);
		} finally {
			sqlSession.close();
		}
		return result;
	}

}
