package naree.jsp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import naree.service.PushService;

@Controller
@RequestMapping("push")
public class PushController {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);
	
	@Autowired
	private PushService pushService;
	
	/**
	 * selectBox 목록읽어오기
	 * @param
	 * @return
	 */
	@RequestMapping(value = "selectBoxList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<String> list(){
		logger.info("push/selectBoxList.do");
		
		List<String> lists = pushService.selectBoxList();
		
		return lists;
	}
}
