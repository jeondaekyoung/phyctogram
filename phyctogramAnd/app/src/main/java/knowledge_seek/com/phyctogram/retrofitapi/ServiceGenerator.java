package knowledge_seek.com.phyctogram.retrofitapi;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import knowledge_seek.com.phyctogram.domain.Comment;
import knowledge_seek.com.phyctogram.domain.Commnty;
import knowledge_seek.com.phyctogram.domain.Height;
import knowledge_seek.com.phyctogram.domain.SqlCommntyListView;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sjw on 2015-12-09.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL =  "http://117.52.89.181";

    private static OkHttpClient httpClient = new OkHttpClient();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass){
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String gubun){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        //구분
        if("RecordActivity".equals(gubun)){
            gsonBuilder.registerTypeAdapter(Height.class, new HeightDes());
        } else if("CommunityWriteActivity".equals(gubun)){
            gsonBuilder.registerTypeAdapter(Commnty.class, new CommntyDes());
        } else if("CommunityListActivity".equals(gubun)){
            gsonBuilder.registerTypeAdapter(SqlCommntyListView.class, new SqlCommntyListViewDes());
        } else if("CommunityViewActivity".equals(gubun)){
            gsonBuilder.registerTypeAdapter(Commnty.class, new CommntyDes());
        } else if("Comment".equals(gubun)){
            gsonBuilder.registerTypeAdapter(Comment.class, new CommentDes());
        } else if("Height".equals(gubun)){
            gsonBuilder.registerTypeAdapter(Height.class, new HeightDes());
        }

        Gson gson = gsonBuilder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);

    }

}
