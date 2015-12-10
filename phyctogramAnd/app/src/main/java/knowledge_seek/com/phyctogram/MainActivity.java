package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-25.
 */
public class MainActivity extends BaseActivity {

    //데이터정의

    //레이아웃정의 - 슬라이드메뉴
    private Button btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_main, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //레이아웃 정의
        btn_left = (Button)findViewById(R.id.btn_left);
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

        //요건되는데, BaseActivity.onResume()에 있으면 안되네..
        //login, join등의 member이 없는 activity가 있기 때문에 안된다.
        //슬라이드메뉴에 있는 내 아이 목록
        updateScreenSlide();

        Log.d("-진우-", "MainActivity 에 onResume() : " + member.toString());

    }



}