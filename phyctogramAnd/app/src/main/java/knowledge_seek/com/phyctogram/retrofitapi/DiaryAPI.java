package knowledge_seek.com.phyctogram.retrofitapi;

import knowledge_seek.com.phyctogram.domain.Diary;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by sjw on 2015-12-29.
 */
public interface DiaryAPI {

    @POST("/rest/diary/registerDiary")
    Call<String> registerDiary(@Body Diary diary);

}
