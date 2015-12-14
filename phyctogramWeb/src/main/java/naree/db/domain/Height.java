package naree.db.domain;

import java.sql.Timestamp;

public class Height {

	private String height_seq;
	private int user_seq;
	private Timestamp mesure_date;
	private double height;
	public String getHeight_seq() {
		return height_seq;
	}
	public void setHeight_seq(String height_seq) {
		this.height_seq = height_seq;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public Timestamp getMesure_date() {
		return mesure_date;
	}
	public void setMesure_date(Timestamp mesure_date) {
		this.mesure_date = mesure_date;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "Height [height_seq=" + height_seq + ", user_seq=" + user_seq + ", mesure_date=" + mesure_date
				+ ", height=" + height + "]";
	}
	
	
}
