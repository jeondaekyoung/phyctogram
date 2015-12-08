package knowledge_seek.com.phyctogram.retrofitapi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * Created by sjw on 2015-12-07.
 */
public class TimestampDes implements JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        long time = Long.parseLong(json.getAsString());
        return new Timestamp(time);
    }
}
