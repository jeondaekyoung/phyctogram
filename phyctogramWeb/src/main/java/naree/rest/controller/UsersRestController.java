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

import naree.db.domain.Analysis;
import naree.db.domain.Height;
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
	 * 내 아이(유저) 삭제(키, 일기도 삭제)
	 * @param user_seq
	 * @return
	 */
	@RequestMapping(value = "delUsersByUserSeq", method = RequestMethod.POST)
	public String delUsersByUserSeq(@RequestParam String user_seq){
		logger.info("delUsersByUserSeq 실행 : " + user_seq);
		
		int result = usersService.delUsersByUserSeq(user_seq);
		
		return "success";
	}
	
	/**
	 * 내 아이(유저) 수정
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "modUsersByUsers", method = RequestMethod.POST)
	public String modUsersByUsers(@RequestBody Users users){
		logger.info("modUsersByUsers 실행 : " + users.toString());
		
		int result = usersService.modifyUsersByUsers(users);
		if(result == 1){
			return "success";
		} else {
			return "fail";
		}
	}
	
	//개월수 -> 19세(228개월)초과시 표준성장도표에 없다
	//19살 넘는얘들은 어떻하지? 19세로하자
	//분석 -> rank가 3%초과시 안나옴
	
	
	/**
	 * 개월수, 캐릭터 계산하기(안드로이드 캐릭터페이지)
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "findMonthNumAnimalByUserSeq", method = RequestMethod.GET)
	public Analysis findMonthNumAnimalByUserSeq(@RequestParam int user_seq){
		logger.info("findMonthNumAnimalByUserSeq 실행 : " + user_seq);
		
		return usersService.findMonthNumAnimalByUserSeq(user_seq);
	}
	
	/**
	 * 메인페이지 내 아이 메인(분석)정보 계산하기
	 * @param user_seq
	 * @return
	 */
	@RequestMapping(value = "findUsersMainInfoByUserSeq", method = RequestMethod.GET)
	public List<Height> findUsersMainInfoByUserSeq(@RequestParam int user_seq){
		logger.info("findUsersMainInfoByUserSeq 실행 : " + user_seq);
		
		return usersService.findUsersMainInfoByUserSeq(user_seq);
	}
	
	/**
	 * 내 아이 키성장 분석
	 * @param user_seq
	 * @return
	 */
	@RequestMapping(value = "findUsersAnalysisByUserSeq", method = RequestMethod.GET)
	public List<Height> findUsersAnalysisByUserSeq(@RequestParam int user_seq){
		logger.info("findUsersAnalysisByUserSeq 실행 : " + user_seq);
		
		return usersService.findUsersAnalysisByUserSeq(user_seq);
	}
	
}
