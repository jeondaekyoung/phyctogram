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

import naree.db.domain.Users;
import naree.service.UsersService;

@RestController
@RequestMapping(value = "rest/users")
public class UsersRestController {

	private static final Logger logger = LoggerFactory.getLogger(UsersRestController.class);
	
	@Autowired
	private UsersService usersService;
	
	/**
	 * 유저 저장하기
	 * @param users
	 * @return
	 */
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
	
	/**
	 * 멤버로 내아이(유저)찾기
	 * @param member_seq
	 * @return
	 */
	@RequestMapping(value = "findUsersByMemberSeq", method = RequestMethod.GET)
	public List<Users> findUsersByMemberSeq(@RequestParam String member_seq){
		logger.info("findUsersByMemberSeq 실행 : " + member_seq);
		
		return usersService.findUsersByMemberSeq(member_seq);
	}
	
	/**
	 * 내 아이(유저) 삭제(키도 삭제)
	 * @param user_seq
	 * @return
	 */
	@RequestMapping(value = "delUsersByUserSeq", method = RequestMethod.POST)
	public String delUsersByUserSeq(@RequestParam String user_seq){
		logger.info("delUsersByUserSeq 실행 : " + user_seq);
		
		int result = usersService.delUsersByUserSeq(user_seq);
		
		return "success";
	}
}
