package knowledge_seek.com.phyctogram.domain;

import java.io.Serializable;

/**
 * Created by sjw on 2015-12-04.
 */
public class Users implements Serializable {

    private int user_seq;
    private String name;
    private String initials;
    private String lifyea;
    private String mt;
    private String de;
    private String sexdstn;
    private int member_seq;

    public int getUser_seq() {
        return user_seq;
    }

    public void setUser_seq(int user_seq) {
        this.user_seq = user_seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getLifyea() {
        return lifyea;
    }

    public void setLifyea(String lifyea) {
        this.lifyea = lifyea;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getSexdstn() {
        return sexdstn;
    }

    public void setSexdstn(String sexdstn) {
        this.sexdstn = sexdstn;
    }

    public int getMember_seq() {
        return member_seq;
    }

    public void setMember_seq(int member_seq) {
        this.member_seq = member_seq;
    }

    @Override
    public String toString() {
        return "Users{" +
                "member_seq=" + member_seq +
                ", sexdstn='" + sexdstn + '\'' +
                ", de='" + de + '\'' +
                ", mt='" + mt + '\'' +
                ", lifyea='" + lifyea + '\'' +
                ", initials='" + initials + '\'' +
                ", name='" + name + '\'' +
                ", user_seq=" + user_seq +
                '}';
    }
}
