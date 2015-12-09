package knowledge_seek.com.phyctogram.retrofitapi;

import java.util.List;

import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.domain.Users;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by sjw on 2015-12-07.
 */
public interface UsersAPI {

    @POST("/rest/users/register")
    Call<String> registerUsers(@Body Users users);

    @GET("/rest/users/findUsersByMemberSeq")
    Call<List<Users>> findByMember(@Query("member_seq") String member_seq);

    @POST("/rest/users/delUsersByUserSeq")
    Call<String> delUsersByUserSeq(@Query("user_seq") String user_seq);

    @POST("/rest/users/modUsersByUsers")
    Call<String> modUsersByUsers(@Body Users users);

}
