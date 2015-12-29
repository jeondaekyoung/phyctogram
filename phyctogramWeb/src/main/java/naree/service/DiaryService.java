package naree.service;

import naree.db.domain.Diary;

public interface DiaryService {

	/**
	 * 일기 저장
	 * @param diary
	 * @return
	 */
	int registerDiary(Diary diary);

}
