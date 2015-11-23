package naree.db.domain;

import java.sql.Timestamp;

public class Query {

	private int query_seq;
	private String title;
	private String contents;
	private Timestamp writng_de;
	private String answer_no;
	private String answer_cn;
	private int member_seq;
	public int getQuery_seq() {
		return query_seq;
	}
	public void setQuery_seq(int query_seq) {
		this.query_seq = query_seq;
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
	public String getAnswer_no() {
		return answer_no;
	}
	public void setAnswer_no(String answer_no) {
		this.answer_no = answer_no;
	}
	public String getAnswer_cn() {
		return answer_cn;
	}
	public void setAnswer_cn(String answer_cn) {
		this.answer_cn = answer_cn;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	@Override
	public String toString() {
		return "Query [query_seq=" + query_seq + ", title=" + title + ", contents=" + contents + ", writng_de="
				+ writng_de + ", answer_no=" + answer_no + ", answer_cn=" + answer_cn + ", member_seq=" + member_seq
				+ "]";
	}
	
}
