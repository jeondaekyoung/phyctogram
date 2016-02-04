package naree.db.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Height {

	private String height_seq;
	private double height;
	private Date mesure_date;
	private int user_seq;
	private Timestamp input_date;
	private int rank;
	private double height_50;
	private String animal_img;
	public String getHeight_seq() {
		return height_seq;
	}
	public void setHeight_seq(String height_seq) {
		this.height_seq = height_seq;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Date getMesure_date() {
		return mesure_date;
	}
	public void setMesure_date(Date mesure_date) {
		this.mesure_date = mesure_date;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public Timestamp getInput_date() {
		return input_date;
	}
	public void setInput_date(Timestamp input_date) {
		this.input_date = input_date;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public double getHeight_50() {
		return height_50;
	}
	public void setHeight_50(double height_50) {
		this.height_50 = height_50;
	}
	public String getAnimal_img() {
		return animal_img;
	}
	public void setAnimal_img(String animal_img) {
		this.animal_img = animal_img;
	}
	@Override
	public String toString() {
		return "Height [height_seq=" + height_seq + ", height=" + height + ", mesure_date=" + mesure_date
				+ ", user_seq=" + user_seq + ", input_date=" + input_date + ", rank=" + rank + ", height_50="
				+ height_50 + ", animal_img=" + animal_img + "]";
	}
}
