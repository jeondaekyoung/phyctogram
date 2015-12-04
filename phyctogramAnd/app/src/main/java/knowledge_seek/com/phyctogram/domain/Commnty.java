package knowledge_seek.com.phyctogram.domain;

import java.sql.Timestamp;

/**
 * Created by sjw on 2015-12-04.
 */
public class Commnty {

    private int commty_seq;
    private String title;
    private String contents;
    private String image_nm;
    private String image_server_nm;
    private Timestamp writng_de;
    private int recomend_co;
    private String comment_no;
    private String comment_cn;
    private int member_seq;

    public int getCommty_seq() {
        return commty_seq;
    }

    public void setCommty_seq(int commty_seq) {
        this.commty_seq = commty_seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImage_nm() {
        return image_nm;
    }

    public void setImage_nm(String image_nm) {
        this.image_nm = image_nm;
    }

    public String getImage_server_nm() {
        return image_server_nm;
    }

    public void setImage_server_nm(String image_server_nm) {
        this.image_server_nm = image_server_nm;
    }

    public Timestamp getWritng_de() {
        return writng_de;
    }

    public void setWritng_de(Timestamp writng_de) {
        this.writng_de = writng_de;
    }

    public int getRecomend_co() {
        return recomend_co;
    }

    public void setRecomend_co(int recomend_co) {
        this.recomend_co = recomend_co;
    }

    public String getComment_no() {
        return comment_no;
    }

    public void setComment_no(String comment_no) {
        this.comment_no = comment_no;
    }

    public String getComment_cn() {
        return comment_cn;
    }

    public void setComment_cn(String comment_cn) {
        this.comment_cn = comment_cn;
    }

    public int getMember_seq() {
        return member_seq;
    }

    public void setMember_seq(int member_seq) {
        this.member_seq = member_seq;
    }

    @Override
    public String toString() {
        return "Commnty{" +
                "commty_seq=" + commty_seq +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", image_nm='" + image_nm + '\'' +
                ", image_server_nm='" + image_server_nm + '\'' +
                ", writng_de=" + writng_de +
                ", recomend_co=" + recomend_co +
                ", comment_no='" + comment_no + '\'' +
                ", comment_cn='" + comment_cn + '\'' +
                ", member_seq=" + member_seq +
                '}';
    }
}
