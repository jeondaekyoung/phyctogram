package knowledge_seek.com.phyctogram;

/**
 * Created by dkfka on 2015-12-04.
 */
public class Listviewitem {

    private String title, userid, date, read, reply;

    public String getName() {
        return title;
    }

    public Listviewitem(String title, String userid, String date, String read) {
        this.title = title;
        this.userid = userid;
        this.date = date;
        this.read = read;
        this.reply = reply;
    }
}
