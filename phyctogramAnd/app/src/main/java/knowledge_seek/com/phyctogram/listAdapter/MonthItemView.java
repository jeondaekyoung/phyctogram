package knowledge_seek.com.phyctogram.listAdapter;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by sjw on 2015-12-29.
 */
public class MonthItemView extends TextView {

    private MonthItem item;

    public MonthItemView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        init();
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        init();
    }

    private void init() {
        setBackgroundColor(Color.WHITE);
    }

    public MonthItem getItem() {
        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;

        int day = item.getDay();
        if (day != 0) {
            setText(String.valueOf(day));
        } else {
            setText("");
        }

    }
}
