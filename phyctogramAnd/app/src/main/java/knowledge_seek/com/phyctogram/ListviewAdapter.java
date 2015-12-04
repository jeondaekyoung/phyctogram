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
public class ListviewAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<Listviewitem> data;
    private int layout;

    public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data) {
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
        return data.get(position).getName();
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
        Listviewitem listviewitem = data.get(position);
        TextView title = (TextView) convertView.findViewById(R.id.community_title);
        TextView userid = (TextView) convertView.findViewById(R.id.community_userid);
        TextView date = (TextView) convertView.findViewById(R.id.community_date);
        TextView read = (TextView) convertView.findViewById(R.id.community_read);
        TextView reply = (TextView) convertView.findViewById(R.id.community_reply);
        title.setText(listviewitem.getName());
        userid.setText(listviewitem.getName());
        date.setText(listviewitem.getName());
        read.setText(listviewitem.getName());
        reply.setText(listviewitem.getName());
        return convertView;
    }
}
