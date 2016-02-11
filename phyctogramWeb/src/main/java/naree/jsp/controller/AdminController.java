package naree.jsp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import naree.db.domain.Admin;
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
}
