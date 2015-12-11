package naree.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Height;
import naree.db.mapper.HeightMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class HeightDaoImpl implements HeightDao {

	@Override
	public void insertHeight(Height insHeight) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			result = heightMapper.insertHeight(insHeight);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}

	}

	@Override
	public List<Height> selectHeightByUser_seq(String user_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Height> heights = new ArrayList<Height>();
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			heights = heightMapper.selectHeightByUser_seq(user_seq);
		}finally{
			sqlSession.commit();
		}

		return heights;
	}

	@Override
	public void deleteHeightByUserSeq(String user_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			result = heightMapper.deleteHeightByUserSeq(user_seq);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		
	}

	@Override
	public List<Height> selectHeightByUserSeqFT(String user_seq, String dateFrom, String dateTo) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Height> heights = new ArrayList<Height>();
		Map<String, String> terms = new HashMap<String, String>();
		terms.put("user_seq", user_seq);
		terms.put("dateFrom", dateFrom);
		terms.put("dateTo", dateTo);
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			heights = heightMapper.selectHeightByUserSeqFT(terms);
		}finally{
			sqlSession.commit();
		}

		return heights;
	}

}
