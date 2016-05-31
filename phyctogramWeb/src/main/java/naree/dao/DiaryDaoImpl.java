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

	/**
	 * 일기 저장
	 * @param diary
	 * @return
	 */
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

	/**
	 * 일기 불러오기
	 * @param user_seq
	 * @param writng_year
	 * @param writng_mt
	 * @return
	 */
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

	/**
	 * 내 아이 일기 지우기
	 * @param user_seq
	 */
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

	/**
	 * 저장된 일기 찾기
	 * @param diary
	 * @return
	 */
	@Override
	public int selectDiaryByDiary(Diary diary) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
			result = diaryMapper.selectDiaryByDiary(diary);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 일기 수정하기
	 * @param diary
	 * @return
	 */
	@Override
	public int updateDiaryByDiary(Diary diary) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
			result = diaryMapper.updateDiaryByDiary(diary);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 일기 삭제하기
	 * @param diary_seq
	 * @return
	 */
	@Override
	public int deleteDiaryByDiary(int diary_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
			result = diaryMapper.deleteDiaryByDiary(diary_seq);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 일기 이미지정보 저장하기
	 * @param diary
	 * @return
	 */
	@Override
	public int updateDiaryForFile(Diary diary) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
			result = diaryMapper.updateDiaryForFile(diary);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}
}
