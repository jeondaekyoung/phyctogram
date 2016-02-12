package knowledge_seek.com.phyctogram.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import knowledge_seek.com.phyctogram.R;

/**
 * Created by dkfka on 2016-02-11.
 */
public class CustomAdapter extends BaseAdapter {

    // 문자열을 보관 할 ArrayList
    private ArrayList<String> m_List;

    // 생성자
    public CustomAdapter() {
        m_List = new ArrayList<String>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if (convertView == null) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_notice, parent, false);

            // TextView에 현재 position의 문자열 추가
            TextView txt_title = (TextView) convertView.findViewById(R.id.notice_title);
            txt_title.setText(m_List.get(position));

            TextView txt_date = (TextView) convertView.findViewById(R.id.notice_date);
            txt_date.setText(m_List.get(position));
        }
        return convertView;
    }
}