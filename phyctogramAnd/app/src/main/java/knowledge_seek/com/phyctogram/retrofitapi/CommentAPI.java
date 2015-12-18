package knowledge_seek.com.phyctogram.retrofitapi;

import java.util.List;

import knowledge_seek.com.phyctogram.domain.Comment;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by sjw on 2015-12-17.
 */
public interface CommentAPI {

    @GET("/rest/comment/findCommentByCommntySeq")
    Call<List<Comment>> findCommentByCommntySeq(@Query("commnty_seq") int commnty_seq);

}
