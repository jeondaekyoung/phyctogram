package naree.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Diary;
import naree.db.mapper.DiaryMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class DiaryDaoImpl implements DiaryDao {

	@Override
	public int insertDiary(Diary diary) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
			result = diaryMapper.insertDiary(diary);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	@Override
	public List<Diary> selectDiaryByUserSeqYearMt(int user_seq, String writng_year, String writng_mt) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Diary> result = new ArrayList<Diary>();
		Diary diary = new Diary();
		diary.setUser_seq(user_seq);
		diary.setWritng_year(writng_year);
		diary.setWritng_mt(writng_mt);
		try {
			DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
			result = diaryMapper.selectDiaryByUserSeqYearMt(diary);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	@Override
	public int deleteDiaryByUserSeq(int user_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
			result = diaryMapper.deleteDiaryByUserSeq(user_seq);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

}
