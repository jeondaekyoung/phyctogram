package naree.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/*import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;*/

import naree.db.domain.Member;
import naree.service.PushService;

@Controller
@RequestMapping("push")
public class PushController {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);

	@Autowired
	private PushService pushService;

	/**
	 * selectBox 목록읽어오기
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "selectBoxList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Member> list() {
		logger.info("push/selectBoxList.do");

		List<Member> lists = pushService.selectBoxList();
		
		System.out.println(lists);

		return lists;
	}

	/**
	 * selectBox 목록읽어오기
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "sendPush.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendPush(@RequestParam(value="token[]") List<String> arrayParams, @RequestParam(value="title") String title, @RequestParam(value="contents") String contents) {
		logger.info("push/sendPush.do"+"title: "+ title+", contents: "+ contents);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arrayParams.size(); i++) {
			System.out.println("push/sendPush.do token: "+arrayParams.get(i));
			// GCM으로부터 발급받은 단말기 RegID 입력. 
	        String regId = arrayParams.get(i);
	        list.add(regId);
		}
		// 프로젝트 서버 API key 입력
        Sender sender = new Sender("AIzaSyA9vY7Rh_e5g-5ZGz_lyaVk4OMRKVIsoNk");
        
        Message message = new Message.Builder().addData("title", title).addData("message", contents)
                .build();
        
        MulticastResult multiResult;
        try {
            multiResult = sender.send(message, list, 5);
            if (multiResult != null) {
                List<Result> resultList = multiResult.getResults();
                for (Result result : resultList) {
                    System.out.println(result.getMessageId());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return "success";
	}
}
