package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-12-03.
 */
public class CommunityListActivity extends BaseActivity implements View.OnClickListener {

    /* slide menu */
    //public static DisplayMetrics metrics;
    //public static LinearLayout ll_mainLayout;
    //public static LinearLayout ll_menuLayout;
    //public static FrameLayout.LayoutParams leftMenuLayoutPrams;
    //public static int leftMenuWidth;
    //public static boolean isLeftExpanded;
    public static Button bt_left;
    public static Button btn1;
    public static Button btn2;
    public static Button btn3;
    public static Button btn4;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_list);

        //커뮤니티 글 목록
        ListView listview = (ListView) findViewById(R.id.list_board);
        ArrayList<Listviewitem> data = new ArrayList<>();
        Listviewitem item = new Listviewitem("안녕", "ar", "12-04", "55");
        data.add(item);
        ListviewAdapter adapter = new ListviewAdapter(this, R.layout.list_community, data);
        listview.setAdapter(adapter);

        //액션바
        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        initSildeMenu();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_left:
                menuLeftSlideAnimationToggle();
                break;
            case R.id.btn1:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                break;
            case R.id.btn2:

                break;
            case R.id.btn3:

                break;
            case R.id.btn4:

                break;

        }

    }

}
