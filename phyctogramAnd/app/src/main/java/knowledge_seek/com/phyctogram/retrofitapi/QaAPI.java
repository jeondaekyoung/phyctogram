package knowledge_seek.com.phyctogram.retrofitapi;

import java.util.List;

import knowledge_seek.com.phyctogram.domain.Qa;
import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by sjw on 2016-02-16.
 */
public interface QaAPI {

    @POST("/rest/qa/findqaByMemberSeq")
    Call<List<Qa>> findqaByMemberSeq(@Query("member_seq") int member_seq,
                                     @Query("pageCnt") int pageCnt);
}
