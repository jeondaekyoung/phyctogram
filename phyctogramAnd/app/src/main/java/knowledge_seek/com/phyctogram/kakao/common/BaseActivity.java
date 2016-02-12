package knowledge_seek.com.phyctogram.kakao.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import android.widget.Toast;

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
import knowledge_seek.com.phyctogram.kakao.common.widget.WaitingDialog;
import knowledge_seek.com.phyctogram.listAdapter.UsersListSlideAdapter;
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
    public static FrameLayout.LayoutParams leftMenuLayoutPrams;
    public static int leftMenuWidth, displayWidth;
    public boolean isLeftExpanded = false;
    public static LinearLayout ll_empty;

    //데이터정의
    public static Member member = null;                 //멤버
    public static List<Users> usersList = new ArrayList<Users>();           //내 아이 목록
    public static Users nowUsers = null;                                        //메인유저
    public static String memberName = null;                                 //슬라이드 멤버 이름
    public static Bitmap memberImg = null;                                  //슬라이드 멤버 이미지


    long backKeyPressedTime = 0;

    //레이아웃 정의
    public ListView lv_usersList;
    public UsersListSlideAdapter usersListSlideAdapter;
    private Button btn_home;                //메인으로가기
    private Button btn_usersDiary;          //육아일기
    private Button btn_dataInput;           //직접입력
    private Button btn_usersManage;     //내아이관리
    private Button btn_setup;               //설정


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
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "BaseActivity.onResume() 실행");
        GlobalApplication.setCurrentActivity(this);
        self = BaseActivity.this;

    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

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
        nowUsers = null;                                      //메인유저
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
    
    //슬라이딩, 메뉴
    public void initSildeMenu() {


        // init left menu width
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        leftMenuWidth = (int) ((metrics.widthPixels) * 0.65);
        displayWidth = metrics.widthPixels;

        // init main view
        ll_mainLayout = (LinearLayout) findViewById(R.id.ll_mainlayout);

        // init left menu
        ll_menuLayout = (LinearLayout) findViewById(R.id.ll_menuLayout);
        leftMenuLayoutPrams = (FrameLayout.LayoutParams) ll_menuLayout.getLayoutParams();
        leftMenuLayoutPrams.width = leftMenuWidth;
        ll_menuLayout.setLayoutParams(leftMenuLayoutPrams);

        ll_empty = (LinearLayout) findViewById(R.id.ll_empty);
        ll_empty.setVisibility(View.GONE);

        if(isLeftExpanded == false) {
            LinearLayout viewGroup = (LinearLayout) findViewById(R.id.ic_leftslidemenu).getParent();
            enableDisableViewGroup(viewGroup, false);
        }


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

    //백버튼 클릭시
    @Override
    public void onBackPressed() {
        Activity nowActivity = GlobalApplication.getCurrentActivity();
        Log.d("-진우-", "지금 실행중인 액티비티 : " + (nowActivity != null ? nowActivity.getClass().getSimpleName() : ""));
        Log.d("-진우-", "시간 : " + backKeyPressedTime);


        Intent intent = null;
        if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("MainActivity")) {
            //두번 클릭시 종료
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                Toast.makeText(getApplicationContext(), "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
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
}
