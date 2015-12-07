package naree.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Member;
import naree.service.MemberService;

@RestController
@RequestMapping(value = "rest/member")
public class MemberRestController {

	private static final Logger logger = LoggerFactory.getLogger(MemberRestController.class);
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 픽토그램 멤버가입
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Member registerMember(@RequestBody Member member){
		logger.info("Member 저장");
		System.out.println(member.toString());
		
		int result = memberService.registerMember(member);
		logger.info("Member 저장 결과 : " + result);
		
		/*if(result == 1){
			return "success";
		} else if(result == 5){
			return "exist";
		} else {
			return "fail";
		}*/
		if(result == 5){
			//이미 픽토그램에 가입된 이메일이다.
			return null;
		} else if(result == 1){
			//가입했거나, 가입된 상태이다.
			Member m = new Member();
			//m = 멤버찾기
			m = memberService.findMemberByJoinRoute(member);
			return m;
		} else {
			//그 밖의 에러상황, 발생할 이유가 없다.
			return null;
		}
	}
	
	/**
	 * 픽토그램 멤버찾기
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "findMemberByMemberSeq", method = RequestMethod.POST)
	public Member findMemberByMemberSeq(@RequestBody int member_seq){
		logger.info("member 찾기 " + member_seq);
		
		Member memberResult = memberService.findMemberByMemberSeq(member_seq);
		return memberResult;
	}
	
	/**
	 * 페이스북 가입 정보로 멤버찾기
	 * @param facebook_id
	 * @param join_route
	 * @return
	 */
	@RequestMapping(value = "findMemberByFacebookInfo", method = RequestMethod.POST)
	public Member findMemberByFacebookInfo(@RequestBody Member member){
		logger.info("member 찾기 " + member.toString());
		
		member = memberService.findMemberByFacebookInfo(member);
		return member;
	}
}
