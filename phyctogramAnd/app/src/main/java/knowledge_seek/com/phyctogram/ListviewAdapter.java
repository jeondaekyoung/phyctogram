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

//커뮤니티 게시판 목록

public class ListviewAdapter extends BaseAdapter {
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
        return data.get(position).getTitle();
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
        title.setText(listviewitem.getTitle());
        userid.setText(listviewitem.getUserid());
        date.setText(listviewitem.getDate());
        read.setText(listviewitem.getRead());
        reply.setText(listviewitem.getReply());
        return convertView;
    }
}


//기록 조회하기 목록
class ListviewAdapter_rec extends BaseAdapter {
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



//커뮤니티 댓글 목록
class ListviewAdapter_reply extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Listviewitem_reply> data;
    private int layout;

    public ListviewAdapter_reply(Context context, int layout, ArrayList<Listviewitem_reply> data) {
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
        return data.get(position).getUserid();
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
        Listviewitem_reply listviewitem_reply = data.get(position);
        TextView userId = (TextView) convertView.findViewById(R.id.community_userid);
        TextView replyContent = (TextView) convertView.findViewById(R.id.community_reply_content);
        userId.setText(listviewitem_reply.getUserid());
        replyContent.setText(listviewitem_reply.getReply_content());
        return convertView;
    }
}