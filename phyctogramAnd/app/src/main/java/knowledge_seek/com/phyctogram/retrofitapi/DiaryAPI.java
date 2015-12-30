package knowledge_seek.com.phyctogram.retrofitapi;

import java.util.List;

import knowledge_seek.com.phyctogram.domain.Diary;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by sjw on 2015-12-29.
 */
public interface DiaryAPI {

    @POST("/rest/diary/registerDiary")
    Call<String> registerDiary(@Body Diary diary);

    @GET("/rest/diary/findDiaryByUserSeqYearMt")
    Call<List<Diary>> findDiaryByUserSeqYearMt(@Query("user_seq") int user_seq,
                                         @Query("writng_year") String writng_year, @Query("writng_mt") String writng_mt);

}
