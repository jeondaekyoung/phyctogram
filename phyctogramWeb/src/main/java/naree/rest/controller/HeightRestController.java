package naree.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Height;
import naree.service.HeightService;

@RestController
@RequestMapping(value = "rest/height")
public class HeightRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(HeightRestController.class);
	
	@Autowired
	private HeightService heightService;
	
	/**
	 * 내 아이(유저) 기록조회
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "findHeightByUserSeqFT", method = RequestMethod.POST)
	public List<Height> findHeightByUserSeqFT(@RequestParam("user_seq") String user_seq,
			@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo){
		logger.info("findHeightByUsers 실행 : " + user_seq + ", " + dateFrom + ", " + dateTo);
		
		List<Height> heights = new ArrayList<Height>();
		heights = heightService.findHeightByUserSeqFT(user_seq, dateFrom, dateTo);
		
		return heights;
	}
	
	/**
	 * 키 삭제하기
	 * @param height
	 * @return
	 */
	@RequestMapping(value = "delHeightByHeightSeq", method = RequestMethod.DELETE)
	public String delHeightByHeightSeq(@RequestParam String height_seq){
		logger.info("delHeightByHeight 실행 : " + height_seq);
		int result = heightService.delHeightByHeightSeq(height_seq);
		if(result == 1){
			return "success";
		} else {
			return "fail";
		}
	}

}
