package naree.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Commnty;
import naree.service.CommntyService;

@RestController
@RequestMapping(value = "rest/commnty")
public class CommntyRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommntyRestController.class);
	
	@Autowired
	private CommntyService commntyService;
	
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
	
	
	
}
