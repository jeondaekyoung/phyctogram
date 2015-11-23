package naree.ex.xml.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naree.ex.xml.domain.Message;
import naree.ex.xml.domain.MessageXml;

@RestController
public class HelloWorldRestXMLController {

	@RequestMapping("/hello/{player}")
	public Message message(@PathVariable String player){
		Message msg = new Message(player, "Hello " + player);
		return msg;
	}
	
	@RequestMapping("/hello1/{player}")
	public MessageXml messageXml(@PathVariable String player){
		MessageXml msg = new MessageXml(player, "Hello " + player);
		return msg;
	}
}
