package naree.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Member;

@RestController
@RequestMapping(value = "rest/test")
public class TestRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestRestController.class);
	
	@RequestMapping(value = "testget0", method = RequestMethod.GET)
	public void testget0(){
		logger.info("testgeto - 실행");
	}
	
	@RequestMapping(value = "testget1/{player}", method = RequestMethod.GET)
	public void testget1(@PathVariable String player){
		logger.info("testget1 - 실행 : " + player);
	}
	
	//Response에 JSON으로 옮
	@RequestMapping(value = "testget2", method = RequestMethod.GET, headers="Accept=application/json")
	public Member testget2(){
		logger.info("testget2 - 실행 ");
		
		Member m = new Member();
		m.setEmail("testget2@naree.com");
		return m;
	}
	
	//Response에 JSON으로 옮
	@RequestMapping(value = "testget3", method = RequestMethod.GET)
	public Member testget3(){
		logger.info("testget3 - 실행 ");
		
		Member m = new Member();
		m.setEmail("testget3@naree.com");
		return m;
	}
	
	//http://192.168.123.103:8080/phyctogramWeb/rest/test/testget4?id=100
	@RequestMapping(value = "testget4", method = RequestMethod.GET)
	public Member testget4(@RequestParam(value = "id", required = false, defaultValue = "1") Integer id){
		logger.info("testget4 - 실행 ");
		
		Member m = new Member();
		m.setMember_seq(id);
		System.out.println(m.getMember_seq());			// 100 출력
		//http://192.168.123.103:8080/phyctogramWeb/rest/test/testget4 이와같이 값이 없을 경우는 1출력
		return m;
	}
	
	//@RestController = @Controller + @ResponseBody
	@RequestMapping(value = "testget5", method = RequestMethod.GET)
	public @ResponseBody Member testget5(){
		logger.info("testget5 - 실행 ");
		
		Member m = new Member();
		m.setEmail("testget5@naree.com");
		return m;
	}
	
	
	@RequestMapping(value = "testget6", method = RequestMethod.POST)
	public Member testget6(@RequestBody String email){
		logger.info("testget6 - 실행 ");
		Member member = new Member();
		System.out.println(member.getEmail());
		
		System.out.println(email);			//{ "email" : "aaa@aaa.aaa" }
		return member;
	}
	
	@RequestMapping(value = "testget7", method = RequestMethod.POST)
	public Member testget7(String email){
		logger.info("testget7 - 실행 ");
		Member member = new Member();
		System.out.println(member.getEmail());
		
		System.out.println(email);//null
		return member;
	}
	
	
	//http://192.168.123.103:9090/phyctogramWeb/rest/test/testget8
	//Payload의 (Headers가 아니다) Raw에  { "aaa":"aaa@aaa.aaa", "bbb":"bbb@@bbb.bbb"}를 입력했다.
	//따옴표가 없으면 에러가 난다.
	@RequestMapping(value = "testget8", method = RequestMethod.POST)
	public Member testget8(@RequestBody TestDomail testdomail){
		logger.info("testget8 - 실행 ");
		Member member = new Member();
		//System.out.println(member.getEmail());
		
		System.out.println(testdomail.toString());  //testDomail [aaa=aaa@aaa.aaa, bbb=bbb@@bbb.bbb]
		return member;
	}
	
	@RequestMapping(value = "testget9", method = RequestMethod.POST, produces="application/json")
	public Member testget9(@RequestBody TestDomail testdomail){
		logger.info("testget9 - 실행 ");
		Member member = new Member();
		//System.out.println(member.getEmail());
		
		System.out.println(testdomail.toString());  //testDomail [aaa=aaa@aaa.aaa, bbb=bbb@@bbb.bbb]
		//@RequestBody 가 없으면 testDomail [aaa=null, bbb=null]
		return member;
	}
	
	@RequestMapping(value = "testget10", method = RequestMethod.POST)
	public ResponseEntity<TestDomail> testget10(@RequestBody TestDomail testdomail){
		logger.info("testget10 - 실행 ");
				
		System.out.println(testdomail.toString());  //testDomail [aaa=aaa@aaa.aaa, bbb=bbb@@bbb.bbb]
		//@RequestBody 가 없으면 testDomail [aaa=null, bbb=null]
		
		testdomail.setAaa("에잇");
		return new ResponseEntity<TestDomail>(testdomail, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//jsp를 불러오지않음
	@RequestMapping(value = "testget100", method = RequestMethod.GET)
	public String testget100(ModelMap model){
		logger.info("testget100 - 실행 ");
		
		model.addAttribute("msg", "메시지");
		return "restTest";
	}

}
