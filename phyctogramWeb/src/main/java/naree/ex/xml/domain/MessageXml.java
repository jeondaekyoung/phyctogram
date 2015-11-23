package naree.ex.xml.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pizza")
public class MessageXml {

	String name;
	String text;
	public MessageXml(){
		
	}
	public MessageXml(String name, String text) {
		super();
		this.name = name;
		this.text = text;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
