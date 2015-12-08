package knowledge_seek.com.phyctogram.retrofitapi;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import knowledge_seek.com.phyctogram.domain.Member;

/**
 * Created by sjw on 2015-12-01.
 */
public class MemberDes implements JsonDeserializer<Object> {


    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement member = json.getAsJsonObject();
        if(json.getAsJsonObject().get("member") != null){
            member = json.getAsJsonObject().get("member");
        }

        return (new Gson().fromJson( member, Member.class));
    }
}
