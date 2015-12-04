package naree.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Member;
import naree.service.MemberService;

@RestController
@RequestMapping(value = "rest/member")
public class MemberRestController {

	private static final Logger logger = LoggerFactory.getLogger(MemberRestController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerMember(@RequestBody Member member){
		logger.info("Member 저장");
		System.out.println(member.toString());
		
		int result = memberService.registerMember(member);
		logger.info("Member 저장 결과 : " + result);
		
		if(result == 1){
			return "success";
		} else if(result == 5){
			return "exist";
		} else {
			return "fail";
		}
		
	}
}
