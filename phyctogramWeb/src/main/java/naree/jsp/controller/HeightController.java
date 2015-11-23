package naree.jsp.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import naree.db.domain.Height;
import naree.service.HeightService;

@Controller
@RequestMapping("height")
public class HeightController {
	
	private static final Logger logger = LoggerFactory.getLogger(HeightController.class);
	
	@Autowired
	private HeightService heightService;

	//
	@RequestMapping(value = "register.do", method = RequestMethod.GET)
	@ResponseBody
	public void registerHeight(String user_seq, String height){
		//logger.trace("/height/register.do");
		//logger.debug("/height/register.do");
		logger.info("/height/register.do");
		//logger.warn("/height/register.do");
		//logger.error("/height/register.do");
		
		Height insHeight = new Height();
		insHeight.setHeight(Double.valueOf(height));
		/*SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date parsed = null;;
		try {
			parsed = transFormat.parse(mesure_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		insHeight.setMesure_date(new Date(parsed.getTime()));*/
		insHeight.setUser_seq(Integer.valueOf(user_seq));
		System.out.println(insHeight.toString());
		
		heightService.registerHeight(insHeight);
		
	}
	
	/**
	 * user_seq의 height 불러오기(jsp)
	 * @return
	 */
	@RequestMapping(value = "data.do", method = RequestMethod.GET)
	public ModelAndView selectHeightByUser_seq(String user_seq){
		logger.warn("data.do 실행 - user_seq : " + user_seq);
		
		ModelAndView mv = new ModelAndView();
		
		List<Height> heights = heightService.selectHeightByUser_seq(user_seq);
		for(Height h : heights){
			System.out.println(h.toString());
		}
		mv.addObject("heights", heights);
		
		mv.setViewName("/height/data");
		return mv;
	}
	
}
