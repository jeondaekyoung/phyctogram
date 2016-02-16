package naree.db.domain;

import java.sql.Timestamp;

public class Qa {

	private int qa_seq;
	private String title;
	private String contents;
	private Timestamp writng_de;
	private String answer;
	private int member_seq;
	private String state;
	public int getQa_seq() {
		return qa_seq;
	}
	public void setQa_seq(int qa_seq) {
		this.qa_seq = qa_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Qa [qa_seq=" + qa_seq + ", title=" + title + ", contents=" + contents + ", writng_de=" + writng_de
				+ ", answer=" + answer + ", member_seq=" + member_seq + ", state=" + state + "]";
	}
		
}
