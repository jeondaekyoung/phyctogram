package naree.jsp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import naree.db.domain.Notice;
import naree.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 공지사항 저장
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "register.do", method=RequestMethod.POST)
	public ModelAndView register(Notice notice){
		logger.info("notice/register.do - " + notice.toString());
		ModelAndView mv = new ModelAndView();
		
		
		noticeService.registerNotice(notice);
		
		mv.setViewName("admin/noticeList");
		return mv;
	}
	
	/**
	 * 공지사항 목록읽어오기
	 * @param pageCnt
	 * @return
	 */
	@RequestMapping(value = "list.do", method=RequestMethod.POST)
	@ResponseBody
	public List<Notice> list(int pageCnt){
		logger.info("notice/list.do - 페이지번호 : " + pageCnt);
		
		List<Notice> notices = noticeService.listNotices(pageCnt);
		
		return notices;
	}
}
