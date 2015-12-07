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

import naree.db.domain.Users;
import naree.service.UsersService;

@RestController
@RequestMapping(value = "rest/users")
public class UsersRestController {

	private static final Logger logger = LoggerFactory.getLogger(UsersRestController.class);
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerUsers(@RequestBody Users users){
		logger.info("Users 저장");
		System.out.println(users.toString());
		
		int result = usersService.registerUsers(users);
		if(result == 1){
			return "success";
		} else {
			return "fail";
		}
	}
	
	
	
	
	@RequestMapping(value = "findUsersByMemberSeq", method = RequestMethod.GET)
	public List<Users> findUsersByMemberSeq(@RequestParam String member_seq){
		logger.info("findUsersByMemberSeq 실행");
		List<Users> usersList = new ArrayList<Users>();
		
		
		return usersList;
	}
}
