package naree.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

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
			m.setPassword(null);
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
		
		return memberService.findMemberByFacebookInfo(member);
	}
	
	
	/**
	 * 픽토그램 멤버 로그인하기
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "loginMemberByPhycto", method = RequestMethod.POST)
	public Member loginMemberByPhycto(@RequestBody Member member){
		logger.info("멤버 로그인 하기 : " + member.toString());
		
		return memberService.loginMemberByPhycto(member);
	}
	
	/**
	 * 멤버 탈퇴하기
	 * @param member_seq
	 * @param pw
	 * @return
	 */
	@RequestMapping(value = "withdrawMember", method = RequestMethod.DELETE)
	public String withrawMember(@RequestParam("member_seq") int member_seq, @RequestParam("pw") String pw,
			@RequestParam("join_route") String join_route){
		logger.info("멤버 탈퇴하기 : " + member_seq + ", " + pw + ", " + join_route);
		
		Member member = new Member();
		member.setMember_seq(member_seq);
		member.setPassword(pw);
		member.setJoin_route(join_route);
		
		if(member.getJoin_route().equals("phyctogram")){
			//비밀번호가 맞는지 확인
			int result = memberService.findMemberByMemberSeqPw(member);
			if(result != 1) {
				return "wrongPw";
			}
		}
		
		//수다방, 댓글 데이터 삭제
		int result1 = memberService.deleteCommntyCommentByMemberSeq(member_seq);
		
		//내아이 지우기
		int result2 = memberService.deleteUsersHeightDiaryByMemberSeq(member_seq);
		
		//가입동의 지우기
		int result3 = memberService.deleteJoinAgreByMemberSeq(member_seq);
		
		//문의 지우기(쓰지않으니깐 패스)
		int result4 = 0;
		
		//멤버 지우기
		int result5 = memberService.deleteMemberByMemberSeq(member_seq); 
		
		if(result5 == 1){
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 비밀번호 변경하기
	 * @param member_seq
	 * @param nowpw
	 * @param pw
	 * @param pw1
	 * @return
	 */
	@RequestMapping(value = "modifyPwByMember", method = RequestMethod.POST)
	public String modifyPwByMember(@RequestParam("member_seq") int member_seq, @RequestParam("nowpw") String nowpw, 
			@RequestParam("newpw") String newpw){
		logger.info("비밀번호 변경하기 : " + nowpw + " -> " + newpw + ", 멤버시퀀스 : " + member_seq);
		
		Member member = new Member();
		member.setMember_seq(member_seq);
		member.setPassword(nowpw);
		int result = memberService.findMemberByMemberSeqPw(member);
		if(result != 1) {
			return "wrongPw";
		}
		
		member.setPassword(newpw);
		result = memberService.modifyPwByMember(member);
		if(result == 1){
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 비밀번호 찾기
	 * @param mailAddr
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "findPw", method = RequestMethod.POST)
	public String findPwMember(@RequestParam("mailAddr") String mailAddr, @RequestParam("token") String token){
		logger.info("비밀번호 찾기 mailAddr: " + mailAddr + ", token : " + token);
		
		int result = memberService.findPwMember(mailAddr);
		logger.info("비밀번호 찾기 result: " + result);
		if(result == -1) {
			return "wrongMail";
		}
		
		Member member = new Member();
		
		String[] strArray = {"a","b","c","d","e","f","g","h","j","k","m","n","p","q","r","s","t","u","x","z"};
		
		Random random = new Random();
		String newpw = "";
		for (int i = 0; i < 9; i++) {
			if(i%2==0){
				newpw += strArray[random.nextInt(19)];
			}else{
				newpw += random.nextInt(9)+1+"";
			}
		}
		member.setMember_seq(result);
		member.setPassword(newpw);
		result = memberService.modifyPwByMember(member);
		if(result == -1){
			return "fail";
		} else {
			// 프로젝트 서버 API key 입력
	        Sender sender = new Sender("AIzaSyA9vY7Rh_e5g-5ZGz_lyaVk4OMRKVIsoNk"); 
	        // GCM으로부터 발급받은 단말기 RegID 입력. 
	        String regId = token;
	        Message message = new Message.Builder().addData("title", "Phytogram").addData("message", "변경된 비밀번호는 : "+newpw+" 입니다. 로그인 후 비밀번호를 변경해주세요.")
	                .build();
	        List<String> list = new ArrayList<String>();
	        list.add(regId);
	        MulticastResult multiResult;
	        try {
	            multiResult = sender.send(message, list, 5);
	            if (multiResult != null) {
	                List<Result> resultList = multiResult.getResults();
	                for (Result results : resultList) {
	                    System.out.println(results.getMessageId());
	                }
	            }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return "success";
		}
	}
	
	/**
	 * Save Device Token
	 * @param member_seq
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "registerToken", method = RequestMethod.POST)
	public String registerToken(@RequestParam("member_seq") int member_seq, @RequestParam("token") String token){
		logger.info("토큰 저장 Token : " + token + ", 멤버시퀀스 : " + member_seq);
		
		Member member = new Member();
		member.setMember_seq(member_seq);
		member.setToken(token);
		
		String resultStr = memberService.findMemberByToken(member);
		if(resultStr==null){
			int result = memberService.registerToken(member);
			if(result == 1){
				return "success";
			} else {
				return "fail";
			}
		}else if(resultStr!=null&&resultStr.equals(token)){
			return "success";
		}else{
			int result = memberService.updateToken(member);
			if(result == 1){
				return "success";
			} else {
				return "fail";
			}
		}
	}
}
