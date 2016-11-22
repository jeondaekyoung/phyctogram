package naree.jsp.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import naree.db.domain.Height;
import naree.service.HeightService;
import naree.service.UsersService;

@Controller
@RequestMapping("height")
@ServerEndpoint(value="/echo")
public class HeightController {

	private static final Logger logger = LoggerFactory.getLogger(HeightController.class);

	private static final java.util.Set<Session> sessions = java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());

	private static final java.util.Map<String,Session> USER = java.util.Collections.synchronizedMap(new java.util.HashMap<String,Session>());
	@Autowired
	private UsersService usersService;

	@Autowired
	private HeightService heightService;

	//키 입력
	@RequestMapping(value = "register.do", method = RequestMethod.GET)
	@ResponseBody
	public void registerHeight(String user_seq, String height,Model model){
		logger.info("/height/register.do : " + user_seq + ", " + height);
		if(height!=null){
			Height insHeight = new Height();
			insHeight.setHeight(Double.valueOf(height));
			insHeight.setUser_seq(Integer.valueOf(user_seq));
			System.out.println(insHeight.toString());
			heightService.registerHeight(insHeight);
			try {
				if(USER.get(user_seq)!=null){
					List<Height> heights=usersService.findUsersMainInfoByUserSeq(Integer.parseInt(user_seq));
					for (int i = 0; i < heights.size() - 1; i++) {
						heights.get(i).setGrow(String.format("%.1f", (heights.get(i).getHeight() - heights.get(i + 1).getHeight())));
					}
					//상위
					String rank = String.valueOf(heights.get(0).getRank());
					//내아이 이미지
					String imgName =heights.get(0).getAnimal_img().substring(0, 12);
					//성장키
					String grow ="";
					if (heights.size() == 2) {
						if (Double.valueOf(heights.get(0).getGrow()) >= 0) {
							grow=heights.get(0).getGrow();
						}
						else{
							grow="-"; 
						}
					}
					else{
						grow="-";
					}
					String f_height=String.format("%.1f",Float.valueOf(height));
					System.out.println("height:" +  f_height+",rank:"+rank+",grow:"+grow+",imgName:"+imgName);	
					USER.get(user_seq).getBasicRemote().sendText(f_height +","+rank+","+grow+","+imgName);
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
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


	//앱 webView에 로드 되는 view
	@RequestMapping("webSocket.do")
	public String testView(@Param(value = "user_seq")String user_seq,Model model){
		System.out.println("user_seq:"+user_seq);
		//user_seq 저장(웹뷰에서 받은) 
		model.addAttribute("user_seq", user_seq);
		return "/admin/Echo_data";
	}


	@OnOpen
	public void onOpen(Session session){
		System.out.println("Open session id : " + session.getId());
		
		String queryString=session.getQueryString();
		String qs_value=queryString.substring(queryString.indexOf("=")+1, queryString.length());


		sessions.add(session);
		System.out.println("sessions:"+sessions.size());
		USER.put(qs_value,session);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established..! "+queryString);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}


	/**
	 * When a user sends a message to the server, this method will intercept the message
	 * and allow us to react to it. For now the message is read as a String.
	 */
	@OnMessage
	public void onMessage(String message, Session session){
		System.out.println("Message from " + session.getId() + ": " + message);
		System.out.println("USER:"+USER.toString());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to : " + message);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		//sendAllSessionToMessage( session, message );
	}

	@OnError
	public void onError( Throwable e, Session session){
		e.getMessage();
	}

	/**
	 * The user closes the connection.
	 * 
	 * Note: you can't send messages to the client from this method
	 */
	@OnClose
	public void onClose(Session session){
		System.out.println("Session " +session.getId()+" has ended");
		String queryString=session.getQueryString();
		String qs_value=queryString.substring(queryString.indexOf("=")+1, queryString.length());
		sessions.remove(session);
		USER.remove(qs_value);
		System.out.println("sessions:"+sessions.size());
	}

}
