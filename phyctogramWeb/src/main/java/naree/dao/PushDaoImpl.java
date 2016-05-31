package naree.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Member;
import naree.db.mapper.PushMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class PushDaoImpl implements PushDao {
	
	/**
	 * selectBox 목록 읽어오기
	 * @param
	 * @return
	 */
	@Override
	public List<Member> selectBoxList() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Member> result = null;
		try {
			PushMapper pushMapper = sqlSession.getMapper(PushMapper.class);
			result = pushMapper.selectBoxList();
		} finally {
			sqlSession.close();
		}
		return result;
	}
}
