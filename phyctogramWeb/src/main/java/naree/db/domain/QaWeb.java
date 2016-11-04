package naree.db.domain;

import java.sql.Timestamp;

public class QaWeb {
	private int qa_Web_seq;
	private String name;
	private String email;
	private String tel;
	private String contents;
	private Timestamp writng_de;
	private String state ;
	 	
	@Override
	public String toString() {
		return "Qa [qa_Web_seq=" + qa_Web_seq + ", name=" + name + ", email=" + email + ", tel=" + tel
				+ ", contents=" + contents + ", writng_de=" + writng_de + ", state=" + state + "]";
	}	
	
	public int getQa_Web_seq() {
		return qa_Web_seq;
	}

	public void setQa_Web_seq(int qa_Web_seq) {
		this.qa_Web_seq = qa_Web_seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Timestamp getWritng_de() {
		return writng_de;
	}

	public void setWritng_de(Timestamp writng_de) {
		this.writng_de = writng_de;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}
