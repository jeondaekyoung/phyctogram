package knowledge_seek.com.phyctogram.retrofitapi;

import knowledge_seek.com.phyctogram.domain.Commnty;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;

/**
 * Created by sjw on 2015-12-15.
 */
public interface CommntyAPI {

    @POST("/rest/commnty/registerCommnty")
    Call<String> registerCommnty(@Body Commnty commnty);


}
