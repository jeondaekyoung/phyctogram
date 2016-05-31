package naree.jsp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import naree.db.domain.Admin;
import naree.db.domain.Notice;
import naree.db.domain.Qa;
import naree.service.AdminService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;
	
	/**
	 * 픽토그램 관리자 로그인 페이지
	 * @return
	 */
	@RequestMapping(value = "index.do")
	public ModelAndView index(){
		logger.info("/admin/index.do");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("admin/login");
		return mv;
	}
	
	/**
	 * 관리자 로그인
	 * @param admin
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login.do", method=RequestMethod.POST)
	public ModelAndView login(Admin admin, HttpSession session){
		logger.info("admin/login.do - " + admin.toString());
		ModelAndView mv = new ModelAndView();
		
		Admin resultAdmin = adminService.login(admin.getId(), admin.getPw());
		session.setAttribute("adminName", resultAdmin.getId());
		
		mv.setViewName("admin/noticeList");
		return mv;
	}
	
	/**
	 * 구매내역 저장
	 * @param price
	 * @param etc
	 * @return
	 */
	@RequestMapping(value = "buy.do", method=RequestMethod.POST)
	public String buy(String price, String etc){
		logger.info("admin/buy.do - 가격 : " + price +", 기타 : "+etc);
		
		int result = adminService.buy(price, etc);
		
		return result == 1?"success":"fail";
	}
	
	/**
	 * 구매 내역 조회
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "totalPrice.do", method=RequestMethod.GET)
	public ModelAndView totalPrice(){
		logger.info("admin/totalPrice.do");
		ModelAndView mv = new ModelAndView();
		
		int result = adminService.totalPrice();
		mv.addObject("data", result);
		
		mv.setViewName("admin/data");
		return mv;
	}
	
}
