package knowledge_seek.com.phyctogram.listAdapter;

/**
 * Created by dkfka on 2015-12-04.
 */

//커뮤니티 게시판 목록
public class Listviewitem {

    private String title, userid, date, read, reply;

    public String getTitle() {return title; }
    public String getUserid() {return userid; }
    public String getDate() {return date; }
    public String getRead() {return read; }
    public String getReply() {return reply; }

    public Listviewitem(String title, String userid, String date, String read, String reply) {
        this.title = title;
        this.userid = userid;
        this.date = date;
        this.read = read;
        this.reply = reply;
    }
}


//기록 조회하기 목록
class Listviewitem_rec {
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


//커뮤니티 댓글 목록
class Listviewitem_reply {
    private String userid, reply_content;

    public String getUserid() { return userid; }
    public String getReply_content() { return reply_content; }

    public Listviewitem_reply(String userid, String reply_content) {
        this.userid = userid;
        this.reply_content = reply_content;
    }
}


//슬라이드 메뉴 사용자 목록
class Listviewitem_users {
    private String username;

    public String getUsername() { return username; }

    public Listviewitem_users(String userid, String username) {
        this.username = username;
    }
}