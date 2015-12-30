package naree.db.mapper;

import java.util.List;
import java.util.Map;

import naree.db.domain.Diary;

public interface DiaryMapper {

	int insertDiary(Diary diary);

	List<Diary> selectDiaryByUserSeqYearMt(Diary diary);

}
