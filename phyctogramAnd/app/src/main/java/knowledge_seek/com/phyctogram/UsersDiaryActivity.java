package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by sjw on 2015-12-28.
 */
public class UsersDiaryActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;

    //레이아웃 정의


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "UsersDiaryActivity.onCreate() 실행");

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_calendar, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();


        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "슬라이드 클릭", Toast.LENGTH_SHORT).show();
                menuLeftSlideAnimationToggle();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "UsersDiaryActivity.onResume() 실행");

        Log.d("-진우-", "UsersDiaryActivity.onResume() : " + member.toString());

        Log.d("-진우-", "UsersDiaryActivity.onResume() 끝");
    }
}
