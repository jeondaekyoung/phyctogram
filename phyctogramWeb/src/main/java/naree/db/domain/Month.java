package naree.db.domain;

public class Month {
	
	private int month_seq;
	private double age;
	private String representative_value;
	private int month_num;
	public int getMonth_seq() {
		return month_seq;
	}
	public void setMonth_seq(int month_seq) {
		this.month_seq = month_seq;
	}
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	public String getRepresentative_value() {
		return representative_value;
	}
	public void setRepresentative_value(String representative_value) {
		this.representative_value = representative_value;
	}
	public int getMonth_num() {
		return month_num;
	}
	public void setMonth_num(int month_num) {
		this.month_num = month_num;
	}
	@Override
	public String toString() {
		return "Month [month_seq=" + month_seq + ", age=" + age + ", representative_value=" + representative_value
				+ ", month_num=" + month_num + "]";
	}
	

}
