package naree.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Qa;
import naree.service.QaService;

@RestController
@RequestMapping(value = "rest/qa")
public class QaRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(QaRestController.class);
	
	@Autowired
	private QaService qaService;

	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
	@RequestMapping(value = "findqaByMemberSeq", method = RequestMethod.POST)
	public List<Qa> findqaByMemberSeq(@RequestParam("member_seq") int member_seq,
			@RequestParam("pageCnt") int pageCnt){
		logger.info("findqaByMemberSeq 실행 : " + member_seq + ", " + pageCnt);
		
		return qaService.findqaByMemberSeq(member_seq, pageCnt);
	}
	
}
