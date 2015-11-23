package naree.db.domain;

public class Users {

	private int user_seq;
	private String name;
	private String initials;
	private String lifyea;
	private String mt;
	private String de;
	private String sexdstn;
	private int member_seq;
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getLifyea() {
		return lifyea;
	}
	public void setLifyea(String lifyea) {
		this.lifyea = lifyea;
	}
	public String getMt() {
		return mt;
	}
	public void setMt(String mt) {
		this.mt = mt;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getSexdstn() {
		return sexdstn;
	}
	public void setSexdstn(String sexdstn) {
		this.sexdstn = sexdstn;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	@Override
	public String toString() {
		return "Users [user_seq=" + user_seq + ", name=" + name + ", initials=" + initials + ", lifyea=" + lifyea
				+ ", mt=" + mt + ", de=" + de + ", sexdstn=" + sexdstn + ", member_seq=" + member_seq + "]";
	}
	
}
