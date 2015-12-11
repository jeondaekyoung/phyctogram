package knowledge_seek.com.phyctogram.domain;

import java.sql.Timestamp;

/**
 * Created by sjw on 2015-12-11.
 */
public class Height {

    private int user_seq;
    private Timestamp mesure_date;
    private double height;
    private double grow;

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

    public double getGrow() {
        return grow;
    }

    public void setGrow(double grow) {
        this.grow = grow;
    }

    @Override
    public String toString() {
        return "Height{" +
                "user_seq=" + user_seq +
                ", mesure_date=" + mesure_date +
                ", height=" + height +
                ", grow=" + grow +
                '}';
    }
}
