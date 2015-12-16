package naree.db.domain;

import java.sql.Timestamp;

public class SqlCommntyListView {
	
	private int commnty_seq;		//커뮤니티(수다방)_seq
	private String title;				//제목
	private String name;			//작성자 이름
	private Timestamp writng_de;		//작성일자
	private int hits_co;				//조회수
	private int cnt;					//댓글 수
	public int getCommnty_seq() {
		return commnty_seq;
	}
	public void setCommnty_seq(int commnty_seq) {
		this.commnty_seq = commnty_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getWritng_de() {
		return writng_de;
	}
	public void setWritng_de(Timestamp writng_de) {
		this.writng_de = writng_de;
	}
	public int getHits_co() {
		return hits_co;
	}
	public void setHits_co(int hits_co) {
		this.hits_co = hits_co;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "SqlCommntyListView [commnty_seq=" + commnty_seq + ", title=" + title + ", name=" + name + ", writng_de="
				+ writng_de + ", hits_co=" + hits_co + ", cnt=" + cnt + "]";
	}
	

}
