package naree.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.DiaryDao;
import naree.db.domain.Diary;

@Service
public class DiaryServiceImpl implements DiaryService {

	private static final Logger logger = LoggerFactory.getLogger(DiaryServiceImpl.class);
	
	@Autowired
	private DiaryDao diaryDao;

	/**
	 * 일기 저장
	 * @param diary
	 * @return
	 */
	@Override
	public int registerDiary(Diary diary) {
		
		return diaryDao.insertDiary(diary);
	}

	/**
	 * 일기 불러오기
	 * @param user_seq
	 * @param writng_year
	 * @param writng_mt
	 * @return
	 */
	@Override
	public List<Diary> findDiaryByUserSeqYearMt(int user_seq, String writng_year, String writng_mt) {
		
		return diaryDao.selectDiaryByUserSeqYearMt(user_seq, writng_year, writng_mt);
	}

	/**
	 * 저장된 일기가 있는지 확인
	 * @param diary
	 * @return
	 */
	@Override
	public int existDiaryByDiary(Diary diary) {
		
		return diaryDao.selectDiaryByDiary(diary);
	}

	/**
	 * 일기 수정하기
	 * @param diary
	 * @return
	 */
	@Override
	public int modifyDiaryByDiary(Diary diary) {
		
		return diaryDao.updateDiaryByDiary(diary);
	}

	/**
	 * 일기 삭제하기
	 * @param diary_seq
	 * @return
	 */
	@Override
	public int deleteDiaryByDiary(int diary_seq) {
		
		return diaryDao.deleteDiaryByDiary(diary_seq);
	}

	/**
	 * 일기 이미지정보 저장하기
	 * @param diary
	 */
	@Override
	public int modifyDiaryForFile(Diary diary) {
		
		return diaryDao.updateDiaryForFile(diary);
	}	
}
