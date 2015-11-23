package naree.db.domain;

import java.sql.Timestamp;

public class Member {

	private int member_seq;
	private String email;
	private String id;
	private String domain;
	private String password;
	private Timestamp join_de;
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getJoin_de() {
		return join_de;
	}
	public void setJoin_de(Timestamp join_de) {
		this.join_de = join_de;
	}
	@Override
	public String toString() {
		return "Member [member_seq=" + member_seq + ", email=" + email + ", id=" + id + ", domain=" + domain
				+ ", password=" + password + ", join_de=" + join_de + "]";
	}
	
}
