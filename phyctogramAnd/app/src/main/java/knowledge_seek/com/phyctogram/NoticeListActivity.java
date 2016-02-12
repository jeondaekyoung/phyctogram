package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.CustomAdapter;

/**
 * Created by dkfka on 2016-02-11.
 */
public class NoticeListActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;        //슬라이드 내 이름

    //레이아웃정의
    private ListView lv_noticeList;
    private CustomAdapter customAdapter;
    //데이터정의
    private boolean lastListViewVisible = false;        //화면에 리스트의 마지막 아이템이 보여지는지 체크
    private int pageCnt = 0;                            //리스트뷰의 목록 페이지 번호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_notice_list, ic_screen, true);

        //슬라이드 내 이미지, 셋팅
        img_profile = (CircularImageView) findViewById(R.id.img_profile);
        if (memberImg != null) {
            img_profile.setImageBitmap(memberImg);
        }

        //슬라이드 내 이름, 셋팅
        tv_member_name = (TextView) findViewById(R.id.tv_member_name);
        if (memberName != null) {
            tv_member_name.setText(memberName);
        }

        //슬라이드 내 아이 목록(ListView)에서 아이 선택시
        lv_usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*nowUsers = (Users)usersListSlideAdapter.getItem(position);
                Log.d("-진우-", "선택한 아이 : " + nowUsers.toString());
                Toast.makeText(getApplicationContext(), "'" + nowUsers.getName() + "' 아이를 선택하였습니다", Toast.LENGTH_LONG).show();*/

            }
        });
        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        //공지 글 목록
        customAdapter = new CustomAdapter();
        lv_noticeList = (ListView)findViewById(R.id.lv_noticeList);
        lv_noticeList.setAdapter(customAdapter);

        //리스트뷰 클릭 -> 글 보기로 이동
        lv_noticeList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoticeViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "CommunityListActivity.onResume() 실행");

        //슬라이드메뉴 셋팅
        initSildeMenu();

        //슬라이드메뉴 내 아이 목록 셋팅
        usersListSlideAdapter.setUsersList(usersList);
        int height = getListViewHeight(lv_usersList);
        lv_usersList.getLayoutParams().height = height;
        usersListSlideAdapter.notifyDataSetChanged();

        Log.d("-진우-", "CommunityListActivity.onResume() : " + member.toString());

        //새로읽어오기
        pageCnt = 0;
        /*btn_commntyLatest.setBackgroundResource(R.drawable.btn_on);
        btn_commntyPlpular.setBackgroundResource(R.drawable.btn_off);
        FindCommntyLatestTask task2 = new FindCommntyLatestTask(pageCnt);
        task2.execute();*/

        Log.d("-진우-", "CommunityListActivity.onResume() 끝");
    }
}
