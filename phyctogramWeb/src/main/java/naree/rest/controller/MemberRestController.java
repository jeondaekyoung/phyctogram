package naree.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rest/member")
public class MemberRestController {

	private static final Logger logger = LoggerFactory.getLogger(MemberRestController.class);
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(){
		logger.info("로그인하자");
		
		String response = "";
		
		return response;
	}
	
	/*@RequestMapping(value = "loginTest", method = RequestMethod.POST)
	public */
}
