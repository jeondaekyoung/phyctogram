package knowledge_seek.com.phyctogram.retrofitapi;

import com.squareup.okhttp.RequestBody;

import java.util.List;

import knowledge_seek.com.phyctogram.domain.Member;
import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by sjw on 2015-12-01.
 */
public interface MemberAPI {

    @GET("/rest/member/testget")
    public void getFeed(Callback<List<Member>> response);


    @GET("/rest/member/testget")
    Call<Member> getLuxuryMemberGet();

    @FormUrlEncoded
    @POST("/rest/member/testpost")
    Call<Member> getLuxuryMemberPost(@Field("method") String method);

    @FormUrlEncoded
    @POST("/rest/member/testposts")
    Call<List<Member>> getLuxuryMembersPost(@Field("method") String method);

    @FormUrlEncoded
    @POST("/rest/member/testpost1")
    Call<String> testpost1(@Field("member") Member member);


    @FormUrlEncoded
    @POST("/rest/member/testpost2")
    Call<String> testpost2(@Field("member") Member member);

    @GET("/rest/member/testget1")
    Call<String> testget11();





    @GET("/rest/test/testget0")
    Call<Void> testget0();

    @GET("/rest/test/testget1/{player}")
    Call<Void> testget1(@Path("player") String player);

    @GET("/rest/test/testget2")
    Call<Member> testget2();

    @GET("/rest/test/testget3")
    Call<Member> testget3();

    @GET("/rest/test/testget4")
    Call<Member> testget4(@Query("id") Integer id);

    @GET("/rest/test/testget5")
    Call<Member> testget5();

    @POST("/rest/test/testget6")
    Call<Member> testget61(@Body String email);

    @FormUrlEncoded
    @POST("/rest/test/testget6")
    Call<Member> testget62(@Field("email") String email);

    @POST("/rest/test/testget7")
    Call<Member> testget7(@Body String email);

    @POST("/rest/test/testget8")
    Call<Member> testget8(@Body TestDomail testDomail);

    @POST("/rest/test/testget9")
    Call<Member> testget9(@Body TestDomail testDomail);

    @POST("/rest/test/testget10")
    Call<TestDomail> testget10(@Body TestDomail testDomail);


    //여기부터 시작
    @POST("/rest/member/register")
    Call<String> registerMember(@Body Member member);
}
