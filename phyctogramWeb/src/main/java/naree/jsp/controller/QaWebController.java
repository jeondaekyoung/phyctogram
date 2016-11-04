package naree.jsp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import naree.db.domain.QaWeb;
import naree.service.QaWebService;

@Controller
@RequestMapping("QaWeb")
public class QaWebController {

	private static final Logger logger = LoggerFactory.getLogger(QaWebController.class);
	
	@Autowired
	private QaWebService QaWebService;
	
	/**
	 * 문의사항 목록읽어오기
	 * @param pageCnt, searchState
	 * @return
	 */
	@RequestMapping(value = "list.do", method=RequestMethod.POST)
	@ResponseBody
	public List<QaWeb> list(int pageCnt, String searchState){
		logger.info("QaWeb/list.do - 페이지번호 : " + pageCnt + "조회 상태 : " +  searchState);
		
		List<QaWeb> QaWebs = QaWebService.listQaWeb(pageCnt, searchState);
		
		return QaWebs;
	}
	
	/**
	 * 문의사항 답변 저장하기
	 * @param meber_seq, contents
	 * @return
	 */
	@RequestMapping(value = "modify.do", method=RequestMethod.POST)
	@ResponseBody
	public String modify(int QaWeb_seq, String answer){
		logger.info("QaWeb/modify.do - 문의 번호 : " + QaWeb_seq + "답변 내용 : " +  answer);
		
		int result = QaWebService.modifyQaWeb(QaWeb_seq, answer);
		
		return result == 1?"success":"fail";
	}
	
	/**
	 * 문의내용 보기
	 * @param QaWeb_seq
	 * @return
	 */
	@RequestMapping(value = "view.do", method=RequestMethod.GET)
	public ModelAndView view(int QaWeb_seq){
		logger.info("QaWeb/view.do - 문의 번호 : " + QaWeb_seq);
		ModelAndView mv = new ModelAndView();
		
		QaWeb QaWeb = QaWebService.searchByQaWebSeq(QaWeb_seq);
		mv.addObject("QaWeb", QaWeb);
		
		mv.setViewName("admin/QaWebView");
		return mv;
	}
}
