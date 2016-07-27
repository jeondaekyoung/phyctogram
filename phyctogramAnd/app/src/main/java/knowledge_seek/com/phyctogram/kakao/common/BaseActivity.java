package knowledge_seek.com.phyctogram.kakao.common;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.LoginActivity;
import knowledge_seek.com.phyctogram.MainActivity;
import knowledge_seek.com.phyctogram.R;
import knowledge_seek.com.phyctogram.SettingActivity;
import knowledge_seek.com.phyctogram.UsersDataInputActivity;
import knowledge_seek.com.phyctogram.UsersDiaryActivity;
import knowledge_seek.com.phyctogram.UsersManageActivity;
import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.gcm.MyRegistrationIntentService;
import knowledge_seek.com.phyctogram.gcm.QuickstartPreferences;
import knowledge_seek.com.phyctogram.kakao.common.widget.WaitingDialog;
import knowledge_seek.com.phyctogram.listAdapter.UsersListSlideAdapter;
import knowledge_seek.com.phyctogram.phyctogram.SaveSharedPreference;
import knowledge_seek.com.phyctogram.util.AnimationClose;
import knowledge_seek.com.phyctogram.util.AnimationOpen;

/**
 * Created by sjw on 2015-11-26.
 */
public class BaseActivity extends Activity {

    protected static Activity self;

    //슬라이드, 메뉴
    public static DisplayMetrics metrics;
    public static LinearLayout ll_mainLayout;
    public static LinearLayout ll_menuLayout;
    public static FrameLayout.LayoutParams leftMenuLayoutParams;
    public static int leftMenuWidth, displayWidth;
    public boolean isLeftExpanded = false;
    public static LinearLayout ll_empty;

    //데이터정의
    public static Member member = null;                 //멤버
    public static List<Users> usersList = new ArrayList<Users>();           //내 아이 목록
    public static Users nowUsers = new Users();                                        //메인유저
    public static String memberName = null;                                 //슬라이드 멤버 이름
    public static Bitmap memberImg = null;                                  //슬라이드 멤버 이미지

    //제스처
    //private GestureDetector mGestures = null;

    long backKeyPressedTime = 0;

    //레이아웃 정의
    public ListView lv_usersList;
    public UsersListSlideAdapter usersListSlideAdapter;
    private Button btn_home;                //메인으로가기
    private Button btn_usersDiary;          //육아일기
    private Button btn_dataInput;           //직접입력
    private Button btn_usersManage;     //내아이관리
    private Button btn_setup;               //설정
    private TextView tv_logout;

    //GCM
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    /**
     * Instance ID를 이용하여 디바이스 토큰을 가져오는 RegistrationIntentService를 실행한다.
     */
    public void getInstanceIdToken() {
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, MyRegistrationIntentService.class);
            startService(intent);
        }
    }

    /**
     * LocalBroadcast 리시버를 정의한다. 토큰을 획득하기 위한 READY, GENERATING, COMPLETE 액션에 따라 UI에 변화를 준다.
     */
    public void registBroadcastReceiver(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if(action.equals(QuickstartPreferences.REGISTRATION_READY)){
                    // 액션이 READY일 경우
                    Log.d("-진우-", "ACTION READY");
                } else if(action.equals(QuickstartPreferences.REGISTRATION_GENERATING)){
                    // 액션이 GENERATING일 경우
                    Log.d("-진우-", "ACTION GENERATING");
                } else if(action.equals(QuickstartPreferences.REGISTRATION_COMPLETE)){
                    // 액션이 COMPLETE일 경우
                    String token = intent.getStringExtra("token");
                    QuickstartPreferences.token = token;
                    Log.d("-진우-", "ACTION COMPLETE : "+token);
                }
            }
        };
    }

    /**
     * Google Play Service를 사용할 수 있는 환경이지를 체크한다.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("-진우-", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "BaseActivity.onCreate() 실행");
        setContentView(R.layout.activity_base);

        //데이터셋팅

        //슬라이드 내 아이 목록(ListView)
        lv_usersList = (ListView) findViewById(R.id.lv_usersList);
        usersListSlideAdapter = new UsersListSlideAdapter(this);
        lv_usersList.setAdapter(usersListSlideAdapter);

        //슬라이드 내 이동 버튼 정의
        btn_home = (Button) findViewById(R.id.btn_home);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            //Toast.makeText(BaseActivity.this, "메인페이지 가기", Toast.LENGTH_SHORT).show();
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //intent.putExtra("member", member);
                startActivity(intent);
                //finish();
            }
        });
        btn_usersDiary = (Button) findViewById(R.id.btn_usersDiary);
        btn_usersDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "육아일기페이지 가기", Toast.LENGTH_SHORT).show();
                if (usersList == null || usersList.size() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.diaryWriteActivity_registerChild, Toast.LENGTH_LONG).show();
                    return;
                }
                menuLeftSlideAnimationToggle();
                Intent intent = new Intent(getApplicationContext(), UsersDiaryActivity.class);
                //intent.putExtra("member", member);
                startActivity(intent);
                //finish();
            }
        });
        btn_dataInput = (Button) findViewById(R.id.btn_dataInput);
        btn_dataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "직접입력페이지 가기", Toast.LENGTH_SHORT).show();
                if (usersList == null || usersList.size() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.diaryWriteActivity_registerChild, Toast.LENGTH_LONG).show();
                    return;
                }
                menuLeftSlideAnimationToggle();
                Intent intent = new Intent(getApplicationContext(), UsersDataInputActivity.class);
                //intent.putExtra("member", member);
                startActivity(intent);
                //finish();
            }
        });
        btn_usersManage = (Button) findViewById(R.id.btn_usersManage);
        btn_usersManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "내아이관리페이지 가기", Toast.LENGTH_SHORT).show();
                menuLeftSlideAnimationToggle();
                Intent intent = new Intent(getApplicationContext(), UsersManageActivity.class);
                //intent.putExtra("member", member);
                startActivity(intent);
                //finish();
            }
        });
        btn_setup = (Button) findViewById(R.id.btn_setup);
        btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "설정페이지 가기", Toast.LENGTH_SHORT).show();
                menuLeftSlideAnimationToggle();
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                //intent.putExtra("member", member);
                startActivity(intent);
                //finish();
            }
        });

        tv_logout = (TextView) findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
                builder.setMessage(R.string.settingActivity_logout)
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능
                        .setPositiveButton(R.string.commonActivity_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(member.getJoin_route().equals("facebook")){
                                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                                    //accessToken 값이 있다면 로그인 상태라고 판단
                                    if (accessToken != null) {
                                        LoginManager.getInstance().logOut();
                                    }
                                    redirectLoginActivity();
                                } else if(member.getJoin_route().equals("kakao")){
                                    UserManagement.requestLogout(new LogoutResponseCallback() {
                                        @Override
                                        public void onCompleteLogout() {
                                            redirectLoginActivity();
                                        }
                                    });

                                } else if(member.getJoin_route().equals("phyctogram")){
                                    SaveSharedPreference.clearMemberSeq(getApplicationContext());
                                    redirectLoginActivity();
                                }
                            }
                        })
                        .setNegativeButton(R.string.commonActivity_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }//end create

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "BaseActivity.onResume() 실행");
        if(QuickstartPreferences.token == null)
            getToken();

        GlobalApplication.setCurrentActivity(this);
        self = BaseActivity.this;
    }

    public void getToken(){
        registBroadcastReceiver();
        //앱이 실행되어 화면에 나타날때 LocalBoardcastManager에 액션을 정의하여 등록한다.
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_READY));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_GENERATING));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));

        getInstanceIdToken();
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    //백버튼 클릭시
    @Override
    public void onBackPressed() {
        Activity nowActivity = GlobalApplication.getCurrentActivity();
        Log.d("-진우-", "지금 실행중인 액티비티 : " + (nowActivity != null ? nowActivity.getClass().getSimpleName() : ""));
        Log.d("-진우-", "시간 : " + backKeyPressedTime);
        Log.d("-진우-", "슬라이드  : " + isLeftExpanded);
        if(isLeftExpanded) {
            menuLeftSlideAnimationToggle();
            return;
        }

        Intent intent = null;
        if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("MainActivity")) {
            //두번 클릭시 종료
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();

                Toast.makeText(getApplicationContext(), R.string.baseActivity_exit, Toast.LENGTH_SHORT).show();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                //moveTaskToBack(true);
                finish();
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        } else {
            super.onBackPressed();
        }
    }


    ///////////메소드

    private void clearReferences() {
        Activity currActivity = GlobalApplication.getCurrentActivity();
        if (currActivity != null && currActivity.equals(this)) {
            GlobalApplication.setCurrentActivity(null);
        }
    }

    protected static void showWaitingDialog() {
        WaitingDialog.showWaitingDialog(self);
    }

    protected static void cancelWaitingDialog() {
        WaitingDialog.cancelWaitingDialog();
    }

    protected void redirectLoginActivity() {
        //데이터초기화
        member = null;                                       //멤버
        usersList.clear();                                      //내 아이 목록
        nowUsers = new Users();                                      //메인유저
        memberName = null;                                //슬라이드 멤버 이름
        memberImg = null;                                  //슬라이드 멤버 이미지
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //finish();
    }

    protected void redirectSignupActivity() {
        Log.d("-진우", "SampleSignupActivity 불러온다.");
        final Intent intent = new Intent(this, SampleSignupActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        //finish();
    }


    /**
     * left menu toggle
     */
    public void menuLeftSlideAnimationToggle() {
        Log.d("-진우-", "슬라이드 : " + isLeftExpanded);
        if (!isLeftExpanded) {
            isLeftExpanded = true;

            // Expand
            new AnimationOpen(ll_mainLayout, leftMenuWidth, displayWidth,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.65f, 0, 0.0f, 0, 0.0f);

            // disable all of main view
            LinearLayout viewGroup = (LinearLayout) findViewById(R.id.ic_leftslidemenu).getParent();
            enableDisableViewGroup(viewGroup, true);

            // enable empty view
            ll_empty.setVisibility(View.VISIBLE);
            ll_empty.setEnabled(true);
            ll_empty.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    menuLeftSlideAnimationToggle();
                    return true;
                }
            });

        } else {
            isLeftExpanded = false;

            // close
            new AnimationClose(ll_mainLayout, leftMenuWidth,
                    TranslateAnimation.RELATIVE_TO_SELF, 0.65f,
                    TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f);

            // enable all of main view
            LinearLayout viewGroup = (LinearLayout) findViewById(R.id.ic_leftslidemenu).getParent();
            enableDisableViewGroup(viewGroup, false);

            // disable empty view
            ll_empty.setVisibility(View.GONE);
            ll_empty.setEnabled(false);

        }
    }

    /**
     * 뷰의 동작을 제어한다. 하위 모든 뷰들이 enable 값으로 설정된다.
     *
     * @param viewGroup
     * @param enabled
     */
    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
            /*if (view.getId() != R.id.bt_left) {
                view.setEnabled(enabled);
                if (view instanceof ViewGroup) {
                    enableDisableViewGroup((ViewGroup) view, enabled);
                }
            }*/
        }
    }

    /*
    * 리스트뷰의 높이를 구함
    * */
    public static int getListViewHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        int listViewHeight = 0;
        listView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        listViewHeight = listView.getMeasuredHeight() * adapter.getCount() + (adapter.getCount() * listView.getDividerHeight());
        return listViewHeight;
    }
    //슬라이딩, 메뉴
    public void initSildeMenu() {
        // init left menu width
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        leftMenuWidth = (int) ((metrics.widthPixels) * 0.65);
        displayWidth = metrics.widthPixels;

        // init main view
        ll_mainLayout = (LinearLayout) findViewById(R.id.ll_mainlayout);
        //메인뷰에 터치이벤트 적용
        //ll_mainLayout.setOnTouchListener(ll_mainLayoutListener);

        // init left menu
        ll_menuLayout = (LinearLayout) findViewById(R.id.ll_menuLayout);
        leftMenuLayoutParams = (FrameLayout.LayoutParams) ll_menuLayout.getLayoutParams();
        leftMenuLayoutParams.width = leftMenuWidth;
        ll_menuLayout.setLayoutParams(leftMenuLayoutParams);

        ll_empty = (LinearLayout) findViewById(R.id.ll_empty);
        ll_empty.setVisibility(View.GONE);

        if(isLeftExpanded == false) {
            LinearLayout viewGroup = (LinearLayout) findViewById(R.id.ic_leftslidemenu).getParent();
            enableDisableViewGroup(viewGroup, false);
        }
    }


}
