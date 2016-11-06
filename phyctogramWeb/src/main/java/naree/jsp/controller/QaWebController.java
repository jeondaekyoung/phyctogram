package naree.jsp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import naree.db.domain.QaWeb;
import naree.service.QaWebService;
import naree.util.Sender_mail;

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
	 * 문의내용 보기
	 * @param qa_Web_seq
	 * @return
	 */
	@RequestMapping(value = "view.do", method=RequestMethod.GET)
	public ModelAndView view(@Param("qa_Web_seq")int qa_Web_seq){
		logger.info("QaWeb/view.do - 문의 번호 : " + qa_Web_seq);
		ModelAndView mv = new ModelAndView();
		
		QaWeb QaWeb = QaWebService.searchByQaWebSeq(qa_Web_seq);
		mv.addObject("QaWeb", QaWeb);
		
		mv.setViewName("admin/qaView-web");
		return mv;
	}
	
	
	/**
	 * 문의내용 저장 (사용자)
	 * @param qaWeb
	 * @return
	 */
	@RequestMapping(value = "write.do", method=RequestMethod.POST)
	public String write(QaWeb qaWeb){
		logger.info("QaWeb/write.do - 문의 하기 : " + qaWeb);
		
		QaWebService.registerQaWeb(qaWeb);
		
		return "redirect:/contact.jsp";
	}
	/**
	 * 문의내용 답변
	 * @param QaWeb_seq,model
	 * @return
	 */
	@RequestMapping(value = "answerForm.do", method=RequestMethod.GET)
	public String answerForm(@Param("qa_Web_seq")int qa_Web_seq,Model model){
		logger.info("QaWeb/answerForm.do: 메일로 답변 폼 " + qa_Web_seq);
		
		QaWeb QaWeb = QaWebService.searchByQaWebSeq(qa_Web_seq);
		model.addAttribute("QaWeb", QaWeb);
		
		return "admin/answer_mail";
	}
	/**
	 * 문의내용 답변 메일 발송
	 * @param QaWeb_seq,HttpServletRequest,Model model
	 * @return
	 */
	@RequestMapping(value = "sendMail.do", method=RequestMethod.POST)
	public String sendMail(@Param("qa_Web_seq")int qa_Web_seq,HttpServletRequest request,Model model){
		logger.info("QaWeb/sendMail.do: 메일 보내기  " + qa_Web_seq);
		
		String sender_pw = request.getParameter("sender_pw");
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		String script = Sender_mail.send(sender,sender_pw, receiver, subject, content);
		System.out.println(script);
		model.addAttribute("script",script);
		if(script.contains("성공")){
			QaWebService.updateStateQaWeb(qa_Web_seq);
		}
		
		return "admin/answer_mail";
	}
	
	/**
	 * 문의내용 수동 답변
	 * @param QaWeb_seq
	 * @return
	 */
	@RequestMapping(value = "manual_answer.do", method=RequestMethod.GET)
	@ResponseBody
	public String manual_answer(@Param("qa_Web_seq")int qa_Web_seq,Model model){
		QaWebService.updateStateQaWeb(qa_Web_seq);
		return null;
	}
	
	
}
