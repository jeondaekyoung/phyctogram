package knowledge_seek.com.phyctogram.retrofitapi;

import android.text.format.DateFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import knowledge_seek.com.phyctogram.domain.Height;

/**
 * Created by sjw on 2015-12-14.
 */
public class HeightDes implements JsonDeserializer<Height> {

    final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Height deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String height_seq = json.getAsJsonObject().get("height_seq").getAsString();
        int user_seq = json.getAsJsonObject().get("user_seq").getAsInt();

        //String m = json.getAsJsonObject().get("mesure_date").getAsString();
        //long time = Long.parseLong(json.getAsString());
        long time = Long.parseLong(json.getAsJsonObject().get("mesure_date").getAsString());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);

        /*Date mesure_utilDate = null;

        try {
            mesure_utilDate = df.parse(m);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        String date = DateFormat.format("yyyy-MM-dd", cal).toString();
        double height = json.getAsJsonObject().get("height").getAsDouble();


        return new Height(height_seq, user_seq, date, height);
    }
}
