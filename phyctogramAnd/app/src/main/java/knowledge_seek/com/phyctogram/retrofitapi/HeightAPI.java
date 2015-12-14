package knowledge_seek.com.phyctogram.retrofitapi;

import java.util.List;

import knowledge_seek.com.phyctogram.domain.Height;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by sjw on 2015-12-11.
 */
public interface HeightAPI {

    @POST("/rest/height/findHeightByUserSeqFT")
    Call<List<Height>> findHeightByUserSeqFT(@Query("user_seq") String user_seq,
                                             @Query("dateFrom") String dateFrom, @Query("dateTo") String dateTo);

    @DELETE("/rest/height/delHeightByHeightSeq")
    Call<String> delHeightByHeightSeq(@Query("height_seq") String height_seq);
}
