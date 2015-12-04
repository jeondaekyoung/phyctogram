package knowledge_seek.com.phyctogram;

/**
 * Created by dkfka on 2015-12-04.
 */
public class Listviewitem_rec {
    private String date, height, grow;

    public String getDate() {
        return date;
    }

    public String getHeight() {
        return height;
    }

    public String getGrow() {
        return grow;
    }

    public Listviewitem_rec(String date, String height, String grow) {
        this.date = date;
        this.height = height;
        this.grow = grow;
    }
}
