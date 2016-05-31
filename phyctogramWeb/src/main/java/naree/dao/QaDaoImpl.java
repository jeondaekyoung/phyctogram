package naree.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Notice;
import naree.db.domain.Qa;
import naree.db.mapper.NoticeMapper;
import naree.db.mapper.QaMapper;
import naree.db.mapper.UsersMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class QaDaoImpl implements QaDao {

	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
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

	/**
	 * 문의내용 저장하기
	 * @param qa
	 * @return
	 */
	@Override
	public int insertQa(Qa qa) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			QaMapper qaMapper = sqlSession.getMapper(QaMapper.class);
			result = qaMapper.insertQa(qa);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 문의사항 목록 읽어오기
	 * @param pageCnt, searchState
	 * @return
	 */
	@Override
	public List<Qa> listQa(int pageCnt, String searchState) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Qa> result = new ArrayList<Qa>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageCnt",  pageCnt*10);
		map.put("searchState", searchState);
		try {
			QaMapper qaMapper = sqlSession.getMapper(QaMapper.class);
			result = qaMapper.listQa(map);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 문의사항 답변 저장하기
	 * @param qa_seq, answer
	 * @return
	 */
	@Override
	public int modifyQa(int qa_seq, String answer) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("qa_seq",  qa_seq);
		map.put("answer", answer);
		try {
			QaMapper qaMapper = sqlSession.getMapper(QaMapper.class);
			result = qaMapper.modifyQa(map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 문의사항 읽어오기
	 * @param qa_seq
	 * @return
	 */
	@Override
	public Qa selectByQaSeq(int qa_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Qa result = null;
		try {
			QaMapper qaMapper = sqlSession.getMapper(QaMapper.class);
			result = qaMapper.selectByQaSeq(qa_seq);
		} finally {
			sqlSession.close();
		}
		return result;
	}
}
