package naree.util;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator {
	
	String id;
	String pw;
	
	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public PasswordAuthentication getPasswordAuthentication() {
	        // 네이버나 Gmail 사용자 계정 설정.
	        // Gmail의 경우 @gmail.com을 제외한 아이디만 입력한다.
		System.out.println("id:"+id+" pw:"+pw);
	        return new PasswordAuthentication(id,pw);
	    }
}
