package naree.dao;

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

}
