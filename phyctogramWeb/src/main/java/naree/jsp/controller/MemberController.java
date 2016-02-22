package naree.jsp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import naree.db.domain.Users;
import naree.service.UsersService;

@Controller
@RequestMapping("member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "users.do", method = RequestMethod.GET)
	@ResponseBody
	public String users(int member_seq){
		logger.info("users.do 실행 : " + member_seq);
		
		List<Users> users = usersService.findUsersByMemberSeq(String.valueOf(member_seq));
		
		
		StringBuilder result = new StringBuilder();
		for(int i=0; i < users.size(); i++){
			result.append(users.get(i).getUser_seq()).append(":").append(users.get(i).getInitials());
			if(i < users.size()-1){
				result.append(",");
			}
		}
		
		return result.toString();
	}
}
