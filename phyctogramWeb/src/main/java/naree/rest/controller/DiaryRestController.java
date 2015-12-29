package naree.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
