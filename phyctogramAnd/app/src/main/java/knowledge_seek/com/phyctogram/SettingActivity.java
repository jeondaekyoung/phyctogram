package knowledge_seek.com.phyctogram;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.phyctogram.SaveSharedPreference;

/**
 * Created by dkfka on 2015-12-02.
 */
public class SettingActivity extends BaseActivity {

    //데이터정의

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의
    private TextView tv_notice;     //공지사항
    private TextView tv_equip;      //내기기
    private TextView tv_logout;     //로그아웃
    private TextView tv_pwmod;      ///비밀번호 변경
    private TextView tv_userdrop;       //회원탈퇴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_setting, ic_screen, true);
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
        //공지사항
        tv_notice = (TextView)findViewById(R.id.tv_notice);
        tv_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "공지사항 클릭");
            }
        });
        //내기기
        tv_equip = (TextView)findViewById(R.id.tv_equip);
        tv_equip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "내기기 클릭");
                /*Intent equip = new Intent(this, EquipmentActivity.class);
                startActivity(equip);*/
            }
        });
        //로그아웃
        tv_logout = (TextView)findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "로그아웃 클릭");
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("로그아웃 하시겠습니까?")
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(member.getJoin_route().equals("facebook")){
                                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                                    //accessToken 값이 있다면 로그인 상태라고 판단
                                    if (accessToken != null) {
                                        LoginManager.getInstance().logOut();
                                    }
                                    finish();
                                    redirectLoginActivity();
                                } else if(member.getJoin_route().equals("kakao")){
                                    UserManagement.requestLogout(new LogoutResponseCallback() {
                                        @Override
                                        public void onCompleteLogout() {
                                            finish();
                                            redirectLoginActivity();
                                        }
                                    });

                                } else if(member.getJoin_route().equals("phyctogram")){
                                    SaveSharedPreference.clearMemberSeq(getApplicationContext());
                                    finish();
                                    redirectLoginActivity();
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        //비밀번호 변경
        tv_pwmod = (TextView)findViewById(R.id.tv_pwmod);
        tv_pwmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "비밀번호 변경");
                /*Intent pwmod = new Intent(this, PwmodActivity.class);
                startActivity(pwmod);*/
            }
        });
        //회원탈퇴
        tv_userdrop = (TextView)findViewById(R.id.tv_userdrop);
        tv_userdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "회원탈퇴");
                /*Intent userdrop = new Intent(this, UsersDropActivity.class);
                startActivity(userdrop);*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //요건되는데, BaseActivity.onResume()에 있으면 안되네..
        //login, join등의 member이 없는 activity가 있기 때문에 안된다.
        updateScreenSlide();

        Log.d("-진우-", "SettingActivity 에서 onResume() : " + member.toString());
    }

}