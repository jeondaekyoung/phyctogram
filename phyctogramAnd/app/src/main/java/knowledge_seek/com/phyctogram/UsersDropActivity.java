package knowledge_seek.com.phyctogram;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-27.
 */
public class UsersDropActivity extends BaseActivity {

    //데이터정의

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_users_drop, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //레이아웃 정의
        btn_left = (ImageButton)findViewById(R.id.btn_left);
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
        Log.d("-진우-", "MainActivity 에서 onResume() : " + member.toString());

        //요건되는데, BaseActivity.onResume()에 있으면 안되네..
        //login, join등의 member이 없는 activity가 있기 때문에 안된다.
        updateScreenSlide();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dropout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("회원 탈퇴시 모든 데이터가 삭제되므로 복구가 되지 않습니다. 탈퇴 하시겠습니까?")
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
    }
}