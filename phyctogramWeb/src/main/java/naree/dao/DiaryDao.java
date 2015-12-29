package naree.dao;

import naree.db.domain.Diary;

public interface DiaryDao {

	/**
	 * 일기 저장
	 * @param diary
	 * @return
	 */
	int insertDiary(Diary diary);

}
