package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-12-03.
 */
public class CommunityListActivity extends BaseActivity {
    public static final String HTTPADDR = "http://117.52.89.181";

    //레이아웃정의
    private LinearLayout ic_screen;
    private Button btn_left;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //커뮤니티 글 목록
        ListView listview = (ListView) findViewById(R.id.list_board);

        ArrayList<Listviewitem> data = new ArrayList<>();
        Listviewitem item = new Listviewitem("안녕", "ar", "12-04", "55", "11");
        data.add(item);
        Listviewitem item2 = new Listviewitem("안녕", "ar", "12-04", "55", "11");
        data.add(item2);

        ListviewAdapter adapter = new ListviewAdapter(this, R.layout.list_community, data);
        listview.setAdapter(adapter);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_community_list, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();


        //레이아웃 정의
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}