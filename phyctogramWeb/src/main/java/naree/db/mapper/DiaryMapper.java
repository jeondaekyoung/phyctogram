package naree.db.mapper;

import java.util.List;
import java.util.Map;

import naree.db.domain.Diary;

public interface DiaryMapper {

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
	List<Diary> selectDiaryByUserSeqYearMt(Diary diary);

	/**
	 * 내 아이 일기 지우기
	 * @param user_seq
	 */
	int deleteDiaryByUserSeq(int user_seq);

	/**
	 * 저장된 일기 찾기
	 * @param diary
	 * @return
	 */
	int selectDiaryByDiary(Diary diary);

	/**
	 * 일기 수정하기
	 * @param diary
	 * @return
	 */
	int updateDiaryByDiary(Diary diary);

	/**
	 * 일기 삭제하기
	 * @param diary_seq
	 * @return
	 */
	int deleteDiaryByDiary(int diary_seq);

	/**
	 * 일기 이미지정보 저장하기
	 * @param diary
	 * @return
	 */
	int updateDiaryForFile(Diary diary);
}
