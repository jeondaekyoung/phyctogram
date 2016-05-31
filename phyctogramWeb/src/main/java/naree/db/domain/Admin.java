package naree.db.domain;

public class Admin {

	private int admin_seq;
	private String id;
	private String pw;
	public int getAdmin_seq() {
		return admin_seq;
	}
	public void setAdmin_seq(int admin_seq) {
		this.admin_seq = admin_seq;
	}
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
	@Override
	public String toString() {
		return "Admin [admin_seq=" + admin_seq + ", id=" + id + ", pw=" + pw + "]";
	}
}
