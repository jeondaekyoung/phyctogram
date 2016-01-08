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

	/**
	 * 저장된 일기가 있는지 확인
	 * @param diary
	 * @return
	 */
	int existDiaryByDiary(Diary diary);

	/**
	 * 일기 수정하기
	 * @param diary
	 * @return
	 */
	int modifyDiaryByDiary(Diary diary);

	/**
	 * 일기 삭제하기
	 * @param diary_seq
	 * @return
	 */
	int deleteDiaryByDiary(int diary_seq);


}
