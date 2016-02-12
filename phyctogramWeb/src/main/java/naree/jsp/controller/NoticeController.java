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
	
	/**
	 * 공지사항 보기
	 * @param notice_seq
	 * @return
	 */
	@RequestMapping(value = "view.do", method=RequestMethod.GET)
	public ModelAndView view(int notice_seq){
		logger.info("notice/view.do - 공기사항 번호 : " + notice_seq);
		ModelAndView mv = new ModelAndView();
		
		Notice notice = noticeService.searchByNoticeSeq(notice_seq);
		mv.addObject("notice", notice);
		
		mv.setViewName("admin/noticeView");
		return mv;
	}
	
	/**
	 * 공지사항 수정하기(입력받는 화면)
	 * @param notice_seq
	 * @return
	 */
	@RequestMapping(value = "modify.do", method=RequestMethod.POST)
	public ModelAndView modify(int notice_seq){
		logger.info("notice/modify.do - 공지사항 번호 : " + notice_seq);
		ModelAndView mv = new ModelAndView();
		
		Notice notice = noticeService.searchByNoticeSeq(notice_seq);
		mv.addObject("notice", notice);
		
		mv.addObject("mode", "modify");
		mv.setViewName("admin/noticeWrite");
		return mv;
	}
	
	/**
	 * 공지사항 수정하기(DB수정)
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "modeModify.do", method=RequestMethod.POST)
	public ModelAndView modeModify(Notice notice){
		logger.info("notice/modeModify.do - " + notice.toString());
		ModelAndView mv = new ModelAndView();
		
		int result = noticeService.modifyByNotice(notice);
		mv.setViewName("admin/noticeList");
		return mv;
	}
	
	/**
	 * 공지사항 삭제하기
	 * @param notice_seq
	 * @return
	 */
	@RequestMapping(value = "erase.do", method=RequestMethod.POST)
	public ModelAndView erase(int notice_seq){
		logger.info("notice/erase.do - 공지사항 번호 : " + notice_seq);
		ModelAndView mv = new ModelAndView();
		
		int result = noticeService.eraseByNoticeSeq(notice_seq);
		mv.setViewName("admin/noticeList");
		return mv;
	}
}
