package naree.db.domain;

import java.sql.Timestamp;

public class Notice {

	private int notice_seq;
	private String title;
	private String notice;
	private Timestamp writng_de;
	public int getNotice_seq() {
		return notice_seq;
	}
	public void setNotice_seq(int notice_seq) {
		this.notice_seq = notice_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Timestamp getWritng_de() {
		return writng_de;
	}
	public void setWritng_de(Timestamp writng_de) {
		this.writng_de = writng_de;
	}
	@Override
	public String toString() {
		return "Notice [notice_seq=" + notice_seq + ", title=" + title + ", notice=" + notice + ", writng_de="
				+ writng_de + "]";
	}
	
}
