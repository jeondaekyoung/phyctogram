package naree.db.domain;

import java.sql.Timestamp;

public class Member {

	private int member_seq;
	private String email;
	private String name;
	private String id;
	private String domain;
	private String password;
	private Timestamp join_de;
	private String kakao_id;
	private String kakao_nickname;
	private String kakao_thumbnailimagepath;
	private String facebook_id;
	private String facebook_email;
	private String facebook_birthday;
	private String facebook_name;
	private String facebook_gender;
	private String join_route;
	private String token;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getKakao_id() {
		return kakao_id;
	}
	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}
	public String getKakao_nickname() {
		return kakao_nickname;
	}
	public void setKakao_nickname(String kakao_nickname) {
		this.kakao_nickname = kakao_nickname;
	}
	public String getKakao_thumbnailimagepath() {
		return kakao_thumbnailimagepath;
	}
	public void setKakao_thumbnailimagepath(String kakao_thumbnailimagepath) {
		this.kakao_thumbnailimagepath = kakao_thumbnailimagepath;
	}
	public String getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}
	public String getFacebook_email() {
		return facebook_email;
	}
	public void setFacebook_email(String facebook_email) {
		this.facebook_email = facebook_email;
	}
	public String getFacebook_birthday() {
		return facebook_birthday;
	}
	public void setFacebook_birthday(String facebook_birthday) {
		this.facebook_birthday = facebook_birthday;
	}
	public String getFacebook_name() {
		return facebook_name;
	}
	public void setFacebook_name(String facebook_name) {
		this.facebook_name = facebook_name;
	}
	public String getFacebook_gender() {
		return facebook_gender;
	}
	public void setFacebook_gender(String facebook_gender) {
		this.facebook_gender = facebook_gender;
	}
	public String getJoin_route() {
		return join_route;
	}
	public void setJoin_route(String join_route) {
		this.join_route = join_route;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Member [member_seq=" + member_seq + ", email=" + email + ", name=" + name + ", id=" + id + ", domain="
				+ domain + ", password=" + password + ", join_de=" + join_de + ", kakao_id=" + kakao_id
				+ ", kakao_nickname=" + kakao_nickname + ", kakao_thumbnailimagepath=" + kakao_thumbnailimagepath
				+ ", facebook_id=" + facebook_id + ", facebook_email=" + facebook_email + ", facebook_birthday="
				+ facebook_birthday + ", facebook_name=" + facebook_name + ", facebook_gender=" + facebook_gender
				+ ", join_route=" + join_route + ", token=" + token + "]";
	}
}
