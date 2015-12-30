package naree.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Diary;
import naree.service.DiaryService;

@RestController
@RequestMapping(value = "rest/diary")
public class DiaryRestController {

	private static final Logger logger = LoggerFactory.getLogger(DiaryRestController.class);
	
	@Autowired
	private DiaryService diaryService;
	
	/**
	 * 일기 저장
	 * @param diary
	 * @return
	 */
	@RequestMapping(value = "registerDiary", method = RequestMethod.POST)
	public String registerDiary(@RequestBody Diary diary){
		logger.info("registerDiary 실행 : " + diary.toString());
		
		int result = diaryService.registerDiary(diary);
		if(result == 1){
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 일기 불러오기
	 * @param user_seq 내 아이 번호
	 * @param writng_year 년도
	 * @param writng_mt 월
	 * @return
	 */
	@RequestMapping(value = "findDiaryByUserSeqYearMt", method = RequestMethod.GET)
	public List<Diary> findDiaryByUserSeqYearMt(@RequestParam("user_seq") int user_seq, 
			@RequestParam("writng_year") String writng_year, @RequestParam("writng_mt") String writng_mt){
		logger.info("findDiaryByUserSeqYearMt 실행 : " + user_seq + ", " + writng_year + ", " + writng_mt);
		
		return diaryService.findDiaryByUserSeqYearMt(user_seq, writng_year, writng_mt);
	}
	
	
}
