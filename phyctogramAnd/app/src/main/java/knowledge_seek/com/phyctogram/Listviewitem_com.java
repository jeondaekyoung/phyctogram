package knowledge_seek.com.phyctogram;

/**
 * Created by dkfka on 2015-12-04.
 */
public class Listviewitem_com {

    private String title, userid, date, read, reply;

    public String getTitle() {
        return title;
    }

    public String getUserid() {
        return userid;
    }

    public String getDate() {
        return date;
    }

    public String getRead() {
        return read;
    }

    public String getReply() {
        return reply;
    }

    public Listviewitem_com(String title, String userid, String date, String read, String reply) {
        this.title = title;
        this.userid = userid;
        this.date = date;
        this.read = read;
        this.reply = reply;
    }
}
