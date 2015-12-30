package naree.service;

import java.util.List;

import naree.db.domain.Diary;

public interface DiaryService {

	/**
	 * 일기 저장
	 * @param diary
	 * @return
	 */
	int registerDiary(Diary diary);

	/**
	 * 일기 불러오기
	 * @param user_seq
	 * @param writng_year
	 * @param writng_mt
	 * @return
	 */
	List<Diary> findDiaryByUserSeqYearMt(int user_seq, String writng_year, String writng_mt);

}
