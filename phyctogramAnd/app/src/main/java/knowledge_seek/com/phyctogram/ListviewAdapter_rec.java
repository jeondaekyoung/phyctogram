package knowledge_seek.com.phyctogram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dkfka on 2015-12-04.
 */
public class ListviewAdapter_rec extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Listviewitem_rec> data;
    private int layout;

    public ListviewAdapter_rec(Context context, int layout, ArrayList<Listviewitem_rec> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getDate();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        Listviewitem_rec listviewitem_rec = data.get(position);
        TextView recordDate = (TextView) convertView.findViewById(R.id.recordDate);
        TextView recordHeight = (TextView) convertView.findViewById(R.id.recordHeight);
        TextView recordGrow = (TextView) convertView.findViewById(R.id.record_grow);
        recordDate.setText(listviewitem_rec.getDate());
        recordHeight.setText(listviewitem_rec.getHeight());
        recordGrow.setText(listviewitem_rec.getGrow());
        return convertView;
    }
}
