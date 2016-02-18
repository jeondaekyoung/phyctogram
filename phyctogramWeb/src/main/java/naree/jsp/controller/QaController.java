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

import naree.db.domain.Qa;
import naree.service.QaService;

@Controller
@RequestMapping("qa")
public class QaController {

	private static final Logger logger = LoggerFactory.getLogger(QaController.class);
	
	@Autowired
	private QaService qaService;
	
	/**
	 * 문의사항 목록읽어오기
	 * @param pageCnt, searchState
	 * @return
	 */
	@RequestMapping(value = "list.do", method=RequestMethod.POST)
	@ResponseBody
	public List<Qa> list(int pageCnt, String searchState){
		logger.info("qa/list.do - 페이지번호 : " + pageCnt + "조회 상태 : " +  searchState);
		
		List<Qa> qas = qaService.listQa(pageCnt, searchState);
		
		return qas;
	}
	
	/**
	 * 문의사항 답변 저장하기
	 * @param meber_seq, contents
	 * @return
	 */
	@RequestMapping(value = "modify.do", method=RequestMethod.POST)
	@ResponseBody
	public String modify(int qa_seq, String answer){
		logger.info("qa/modify.do - 문의 번호 : " + qa_seq + "답변 내용 : " +  answer);
		
		int result = qaService.modifyQa(qa_seq, answer);
		
		return result == 1?"success":"fail";
	}
	
	/**
	 * 문의내용 보기
	 * @param qa_seq
	 * @return
	 */
	@RequestMapping(value = "view.do", method=RequestMethod.GET)
	public ModelAndView view(int qa_seq){
		logger.info("qa/view.do - 문의 번호 : " + qa_seq);
		ModelAndView mv = new ModelAndView();
		
		Qa qa = qaService.searchByQaSeq(qa_seq);
		mv.addObject("qa", qa);
		
		mv.setViewName("admin/qaView");
		return mv;
	}
}
