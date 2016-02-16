package naree.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Qa;
import naree.db.mapper.QaMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class QaDaoImpl implements QaDao {

	@Override
	public List<Qa> selectqaByMemberSeq(int member_seq, int pageCnt) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Qa> result;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("member_seq", member_seq);
		map.put("pageCnt", pageCnt*10);
		try {
			QaMapper qaMapper = sqlSession.getMapper(QaMapper.class);
			result = qaMapper.selectqaByMemberSeq(map);
		} finally {
			sqlSession.close();
		}
		return result;
	}

}
