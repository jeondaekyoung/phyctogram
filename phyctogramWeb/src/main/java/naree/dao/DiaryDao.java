package naree.dao;

import java.util.List;

import naree.db.domain.Diary;

public interface DiaryDao {

	/**
	 * 일기 저장
	 * @param diary
	 * @return
	 */
	int insertDiary(Diary diary);

	/**
	 * 일기 불러오기
	 * @param user_seq
	 * @param writng_year
	 * @param writng_mt
	 * @return
	 */
	List<Diary> selectDiaryByUserSeqYearMt(int user_seq, String writng_year, String writng_mt);

}
