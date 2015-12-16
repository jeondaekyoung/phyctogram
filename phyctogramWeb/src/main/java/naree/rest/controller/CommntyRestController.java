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

import naree.db.domain.Commnty;
import naree.db.domain.SqlCommntyListView;
import naree.service.CommntyService;

@RestController
@RequestMapping(value = "rest/commnty")
public class CommntyRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommntyRestController.class);
	
	@Autowired
	private CommntyService commntyService;
	
	/**
	 * 커뮤니티(수다방) 글 저장
	 * @param commnty
	 * @return
	 */
	@RequestMapping(value = "registerCommnty", method = RequestMethod.POST)
	public String registerCommnty(@RequestBody Commnty commnty){
		logger.info("registerCommnty 실행 : " + commnty.toString());
		
		int result = commntyService.registerCommnty(commnty);
		if(result == 1){
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 커뮤니티(수다방) 최신 글 목록 읽어오기
	 * @return
	 */
	@RequestMapping(value = "findCommntyLatest", method = RequestMethod.GET)
	public List<SqlCommntyListView> findCommntyLatest(@RequestParam("pageCnt") int pageCnt){
		logger.info("findCommntyLatest 실행 : " + pageCnt);
		
		return commntyService.findCommntyLatest(pageCnt);
		
	}
	
	/**
	 * 커뮤니티(수다방) 인기 글 목록 읽어오기
	 * @return
	 */
	@RequestMapping(value = "findCommntyPopular", method = RequestMethod.GET)
	public List<SqlCommntyListView> findCommntyPopular(@RequestParam("pageCnt") int pageCnt){
		logger.info("findCommntyPopular 실행 " + pageCnt);
		
		return commntyService.findCommntyPopular(pageCnt);
	}
	
	
	
}
