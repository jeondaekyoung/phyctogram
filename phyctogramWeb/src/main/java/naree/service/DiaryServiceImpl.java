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

	@Override
	public int registerDiary(Diary diary) {
		
		return diaryDao.insertDiary(diary);
	}

	@Override
	public List<Diary> findDiaryByUserSeqYearMt(int user_seq, String writng_year, String writng_mt) {
		
		return diaryDao.selectDiaryByUserSeqYearMt(user_seq, writng_year, writng_mt);
	}
}
