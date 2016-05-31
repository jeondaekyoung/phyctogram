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

	/**
	 * 커뮤니티(수다방) 글 저장
	 * @param commnty
	 * @return
	 */
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

	/**
	 * 커뮤니티(수다방) 최신 글 목록 읽어오기
	 * @return
	 */
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

	/**
	 * 커뮤니티(수다방) 인기 글 목록 읽어오기
	 * @return
	 */
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

	/**
	 * 커뮤니티(수다방) 글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	@Override
	public Commnty selectCommntyByCommntySeq(int commnty_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Commnty result;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.selectCommntyByCommntySeq(commnty_seq);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 커뮤니티(수다방) 조회 카운트하기
	 * @param commnty
	 * @return
	 */
	@Override
	public int updateHitsCoByCommnty(Commnty commnty) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.updateHitsCoByCommnty(commnty);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 커뮤니티(수다방) 인기 Top3 목록 읽어오기
	 * @return
	 */
	@Override
	public List<SqlCommntyListView> selectCommntyPopularTop3() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<SqlCommntyListView> result;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.selectCommntyPopularTop3();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 멤버가 쓴 수다방글 읽어오기
	 * @param member_seq
	 * @return
	 */
	@Override
	public List<Commnty> selectCommntyByMemberSeq(int member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Commnty> result;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.selectCommntyByMemberSeq(member_seq);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 멤버가 쓴 수다방글 지우기
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteCommntyByMemberSeq(int member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			CommntyMapper commntyMapper = sqlSession.getMapper(CommntyMapper.class);
			result = commntyMapper.deleteCommntyByMemberSeq(member_seq);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}
}
