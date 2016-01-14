package naree.db.domain;

public class Analysis {
	
	private int analysis_seq;
	private String sexdstn;
	private int month_num;
	private double height;
	private int rank;
	private String animal_img;
	public int getAnalysis_seq() {
		return analysis_seq;
	}
	public void setAnalysis_seq(int analysis_seq) {
		this.analysis_seq = analysis_seq;
	}
	public String getSexdstn() {
		return sexdstn;
	}
	public void setSexdstn(String sexdstn) {
		this.sexdstn = sexdstn;
	}
	public int getMonth_num() {
		return month_num;
	}
	public void setMonth_num(int month_num) {
		this.month_num = month_num;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getAnimal_img() {
		return animal_img;
	}
	public void setAnimal_img(String animal_img) {
		this.animal_img = animal_img;
	}
	@Override
	public String toString() {
		return "Analysis [analysis_seq=" + analysis_seq + ", sexdstn=" + sexdstn + ", month_num=" + month_num
				+ ", height=" + height + ", rank=" + rank + ", animal_img=" + animal_img + "]";
	}
	

}
