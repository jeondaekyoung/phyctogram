package knowledge_seek.com.phyctogram.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by sjw on 2015-12-11.
 */
public class Height {

    private String height_seq;
    private int user_seq;
    //private Timestamp mesure_date;
    private String mesure_date;
    private double height;
    private String grow;

    public Height() {

    }

    public Height(String height_seq, int user_seq, String mesure_utilDate, double height) {
        this.height_seq = height_seq;
        this.user_seq = user_seq;
        this.mesure_date = mesure_utilDate;
        this.height = height;
    }

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

    public String getMesure_date() {
        return mesure_date;
    }

    public void setMesure_date(String mesure_date) {
        this.mesure_date = mesure_date;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGrow() {
        return grow;
    }

    public void setGrow(String grow) {
        this.grow = grow;
    }

    @Override
    public String toString() {
        return "Height{" +
                "height_seq='" + height_seq + '\'' +
                ", user_seq=" + user_seq +
                ", mesure_date='" + mesure_date + '\'' +
                ", height=" + height +
                ", grow='" + grow + '\'' +
                '}';
    }
}
