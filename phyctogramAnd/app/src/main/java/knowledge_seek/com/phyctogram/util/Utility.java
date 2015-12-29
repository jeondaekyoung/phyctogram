package knowledge_seek.com.phyctogram.util;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import knowledge_seek.com.phyctogram.domain.Comment;
import knowledge_seek.com.phyctogram.domain.Commnty;
import knowledge_seek.com.phyctogram.domain.Diary;
import knowledge_seek.com.phyctogram.domain.Height;
import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.domain.Users;

/**
 * Created by sjw on 2015-11-30.
 */
public class Utility {
    private static Pattern pattern;
    private static Matcher matcher;

    //email pattern
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate Email with regular expression
     *
     * @param email
     * @return true for Valid Email and false for Invalid Email
     */
    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
    /**
     * Checks for Null String object
     *
     * @param txt
     * @return true for not null and false for null String object
     */
    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }

    public static String member2json(Member member) {
        String json = new Gson().toJson(member);
        return json;
    }

    public static String users2json(Users users) {
        String json = new Gson().toJson(users);
        return json;
    }

    public static String comment2json(Comment comment) {
        return new Gson().toJson(comment);
    }

    public static String commnty2json(Commnty commnty){
        return new Gson().toJson(commnty);
    }

    public static String height2json(Height height){
        return new Gson().toJson(height);
    }

    public static String diary2json(Diary diary) {
        return new Gson().toJson(diary);
    }
}
