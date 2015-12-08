package knowledge_seek.com.phyctogram.retrofitapi;

import knowledge_seek.com.phyctogram.domain.Users;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by sjw on 2015-12-07.
 */
public interface UsersAPI {

    @POST("/rest/users/register")
    Call<String> registerUsers(@Body Users users);
}
