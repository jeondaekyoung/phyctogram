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
			sqlSession.close();
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
	public List<Height> selectHeightByUserSeqFT(String user_seq, String dateFrom, String dateTo, int pageCntFirstIndex) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Height> heights = new ArrayList<Height>();
		Map<String, Object> terms = new HashMap<String, Object>();
		terms.put("user_seq", user_seq);
		terms.put("dateFrom", dateFrom);
		terms.put("dateTo", dateTo);
		terms.put("pageCntFirstIndex", pageCntFirstIndex);
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			heights = heightMapper.selectHeightByUserSeqFT(terms);
		}finally{
			sqlSession.close();
		}

		return heights;
	}

	@Override
	public String selectHeightSeq() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		String height_seq = null;
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			height_seq = heightMapper.selectHeightSeq();
		}finally{
			sqlSession.close();
		}

		return height_seq;
	}

	@Override
	public int deleteHeightByHeightSeq(String height_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			result = heightMapper.deleteHeightByHeightSeq(height_seq);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	@Override
	public int insertHeightAnd(Height height) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			HeightMapper heightMapper = sqlSession.getMapper(HeightMapper.class);
			result = heightMapper.insertHeightAnd(height);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

}
