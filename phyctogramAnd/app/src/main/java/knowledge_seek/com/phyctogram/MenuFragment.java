package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

import java.util.ArrayList;

public class MenuFragment extends SherlockFragment {

    ListView listView;
    ArrayList<String> alItems;

    public MenuFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] data = {"아람님", "내 아이관리", "육아 일기", "설정"};

        View v = inflater.inflate(R.layout.menu_frame, container, false);

        // 기본 변수 선언
        listView = (ListView) v.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View vi, int pos, long id) {
                Fragment newContent = null;

                switch (pos) {
                    case 0:
                        newContent = new UserMainActivity();
                        break;
                    case 1:
                        newContent = new UserAddActivity();
                        break;
                    case 2:
                        newContent = new CalendarActivity();
                        break;
                    case 3:
                        newContent = new RecordActivity();
                        break;
                }

                if (newContent != null)
                    switchFragment(newContent);
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void switchFragment(Fragment fragment) {
        if (getActivity() == null)
            return;
        if (getActivity() instanceof MainActivity) {
            MainActivity fca = (MainActivity) getActivity();
            fca.switchContent(fragment);
        }
    }
}