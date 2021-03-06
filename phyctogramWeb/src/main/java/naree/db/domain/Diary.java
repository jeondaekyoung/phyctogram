package naree.db.domain;

import org.springframework.web.multipart.MultipartFile;

public class Diary {

	private int diary_seq;
	private String title;
	private String contents;
	private String writng_year;
	private String writng_mt;
	private String writng_de;
	private String image_nm;
	private String image_server_nm;
	private MultipartFile image_file;
	private int user_seq;
	public int getDiary_seq() {
		return diary_seq;
	}
	public void setDiary_seq(int diary_seq) {
		this.diary_seq = diary_seq;
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
	public String getWritng_year() {
		return writng_year;
	}
	public void setWritng_year(String writng_year) {
		this.writng_year = writng_year;
	}
	public String getWritng_mt() {
		return writng_mt;
	}
	public void setWritng_mt(String writng_mt) {
		this.writng_mt = writng_mt;
	}
	public String getWritng_de() {
		return writng_de;
	}
	public void setWritng_de(String writng_de) {
		this.writng_de = writng_de;
	}
	public String getImage_nm() {
		return image_nm;
	}
	public void setImage_nm(String image_nm) {
		this.image_nm = image_nm;
	}
	public String getImage_server_nm() {
		return image_server_nm;
	}
	public void setImage_server_nm(String image_server_nm) {
		this.image_server_nm = image_server_nm;
	}
	public MultipartFile getImage_file() {
		return image_file;
	}
	public void setImage_file(MultipartFile image_file) {
		this.image_file = image_file;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	@Override
	public String toString() {
		return "Diary [diary_seq=" + diary_seq + ", title=" + title + ", contents=" + contents + ", writng_year="
				+ writng_year + ", writng_mt=" + writng_mt + ", writng_de=" + writng_de + ", image_nm=" + image_nm
				+ ", image_server_nm=" + image_server_nm + ", image_file=" + image_file + ", user_seq=" + user_seq
				+ "]";
	}
	
}
