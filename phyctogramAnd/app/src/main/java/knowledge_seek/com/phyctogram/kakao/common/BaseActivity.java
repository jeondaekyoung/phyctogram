package knowledge_seek.com.phyctogram.kakao.common;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

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
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import knowledge_seek.com.phyctogram.util.AnimationClose;
import knowledge_seek.com.phyctogram.util.AnimationOpen;
import retrofit.Call;

/**
 * Created by sjw on 2015-11-26.
 */
public class BaseActivity extends Activity {
    public static final String HTTPADDR = "http://www.phyctogram.com";

    protected static Activity self;

    //슬라이드, 메뉴
    public static DisplayMetrics metrics;
    public static LinearLayout ll_mainLayout;
    public static LinearLayout ll_menuLayout;
    public static FrameLayout.LayoutParams leftMenuLayoutPrams;
    public static int leftMenuWidth;
    public boolean isLeftExpanded = false;
    public LinearLayout ll_empty;

    //데이터정의
    public static Member member = null;
    public static List<Users> usersList = null;
    public static Users nowUsers = null;

    //레이아웃 정의
    public ListView lv_usersList;
    public UsersListSlideAdapter usersListSlideAdapter;
    private Button btn_usersManage;     //내아이관리
    private Button btn_usersDiary;          //육아일기
    private Button btn_dataInput;           //직접입력
    private Button btn_setup;               //설정


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "BaseActivity.onCreate() 실행");
        setContentView(R.layout.activity_main);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            member = (Member) bundle.getSerializable("member");
            Log.d("-진우-", "BaseActivity 에서 onCreate() : " + member.toString());
        } else {
            member = new Member();
            Log.d("-진우-", "BaseActivity 에서 onCreate() : " + member.toString());
        }

        //레이아웃 정의
        //슬라이드 내 아이 목록(ListView)
        lv_usersList = (ListView) findViewById(R.id.lv_usersList);
        usersListSlideAdapter = new UsersListSlideAdapter(this);
        lv_usersList.setAdapter(usersListSlideAdapter);

        //슬라이드 내 이동 버튼 정의
        btn_usersManage = (Button) findViewById(R.id.btn_usersManage);
        btn_usersManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "내아이관리페이지 가기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), UsersManageActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                //menuLeftSlideAnimationToggle();
                finish();
            }
        });
        btn_usersDiary = (Button) findViewById(R.id.btn_usersDiary);
        btn_usersDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "육아일기페이지 가기", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                Intent intent = new Intent(getApplicationContext(), UsersDiaryActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                //menuLeftSlideAnimationToggle();
                finish();
            }
        });
        btn_dataInput = (Button) findViewById(R.id.btn_dataInput);
        btn_dataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "직접입력페이지 가기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), UsersDataInputActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                //menuLeftSlideAnimationToggle();
                finish();
            }
        });
        btn_setup = (Button) findViewById(R.id.btn_setup);
        btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BaseActivity.this, "설정페이지 가기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                //menuLeftSlideAnimationToggle();
                finish();
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
        member = null;
        usersList = null;
        nowUsers = null;
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectSignupActivity() {
        Log.d("-진우", "SampleSignupActivity 불러온다.");
        final Intent intent = new Intent(this, SampleSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }


    //슬라이딩, 메뉴
    public void initSildeMenu() {

        // init left menu width
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        leftMenuWidth = (int) ((metrics.widthPixels) * 0.65);

        // init main view
        ll_mainLayout = (LinearLayout) findViewById(R.id.ll_mainlayout);

        // init left menu
        ll_menuLayout = (LinearLayout) findViewById(R.id.ll_menuLayout);
        leftMenuLayoutPrams = (FrameLayout.LayoutParams) ll_menuLayout.getLayoutParams();
        leftMenuLayoutPrams.width = leftMenuWidth;
        ll_menuLayout.setLayoutParams(leftMenuLayoutPrams);

        ll_empty = (LinearLayout) findViewById(R.id.ll_empty);
        ll_empty.setVisibility(View.GONE);

        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.ic_leftslidemenu).getParent();
        enableDisableViewGroup(viewGroup, false);


    }

    /**
     * left menu toggle
     */
    public void menuLeftSlideAnimationToggle() {
        //Log.d("-진우-", "슬라이드 : " + isLeftExpanded);
        if (!isLeftExpanded) {
            isLeftExpanded = true;

            // Expand
            //DisplayMetrics displaymetrics = new DisplayMetrics();
            //getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            //int displayWidth = displaymetrics.widthPixels;
            metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int displayHeight = metrics.heightPixels;
            int displayWidth = metrics.widthPixels;
            new AnimationOpen(ll_mainLayout, leftMenuWidth, displayWidth,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);

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
                    TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
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

    //지울 것임 BaseActivity에서 통신을 하면, 페이지마다 초기 데이터를 가져오는 통신까지 2번 통신하게 된다.
    //그래서 페이지마다 한 번만 통신하도록 하자.
    public void updateScreenSlide() {
        FindUsersByMemberTask task = new FindUsersByMemberTask();
        task.execute();
        //usersListSlideAdapter.notifyDataSetChanged();
    }

    private class FindUsersByMemberTask extends AsyncTask<Void, Void, List<Users>> {

        private List<Users> usersTask;
        //private ProgressDialog dialog = new ProgressDialog(BaseActivity.this);

        @Override
        protected void onPreExecute() {
            //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //dialog.setMessage("BaseActivity - 내 아이 목록 가져오기");
            //dialog.show();
            super.onPreExecute();
        }

        @Override
        protected List<Users> doInBackground(Void... params) {
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            return usersTask;
        }

        @Override
        protected void onPostExecute(List<Users> userses) {

            if (userses != null && userses.size() > 0) {
                Log.d("-진우-", "내 아이는 몇명? " + userses.size());
                for (Users u : userses) {
                    Log.d("-진우-", "내아이 : " + u.toString());
                }
                usersList = userses;

                usersListSlideAdapter.setUsersList(usersList);
                if (nowUsers == null) {
                    nowUsers = userses.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());
                /*if(tv_users_name != null){
                    tv_users_name.setText(nowUsers.getName());
                }*/
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();

            //dialog.dismiss();
            super.onPostExecute(userses);
        }
    }
    // --> 여기까지 지울 것임

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
        //String nowActivity = GlobalApplication.getCurrentActivity().getClass().getSimpleName();
        Log.d("-진우-", "지금 실행중인 액티비티 : " + (nowActivity != null ? nowActivity.getClass().getSimpleName() : ""));

        Intent intent = null;
        if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("MainActivity")) {
            finish();
        }
        //내 아이 관리 관련 페이지 이동
        else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("UsersAddActivity")) {
            intent = new Intent(getApplicationContext(), UsersManageActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        } else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("UsersModActivity")) {
            intent = new Intent(getApplicationContext(), UsersManageActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        }
        //설정 관련 페이지 이동
        else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("EquipmentActivity")) {
            intent = new Intent(getApplicationContext(), SettingActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        } else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("PwmodActivity")) {
            intent = new Intent(getApplicationContext(), SettingActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        } else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("WithdrawActivity")) {
            intent = new Intent(getApplicationContext(), SettingActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        }
        //육아일기 관련 페이지 이동
        else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("DiaryWriteActivity")) {
            intent = new Intent(getApplicationContext(), UsersDiaryActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        } else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("DiaryViewActivity")) {
            intent = new Intent(getApplicationContext(), UsersDiaryActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        }
        //메인으로 가는 페이지 : 내아이관리, 기록조회, 설정, 직접입력, 육아일기
        else if (nowActivity != null && nowActivity.getClass().getSimpleName().equals("UsersManageActivity") ||
                nowActivity != null && nowActivity.getClass().getSimpleName().equals("RecordActivity") ||
                nowActivity != null && nowActivity.getClass().getSimpleName().equals("SettingActivity") ||
                nowActivity != null && nowActivity.getClass().getSimpleName().equals("UsersDataInputActivity") ||
                nowActivity != null && nowActivity.getClass().getSimpleName().equals("UsersDiaryActivity") ){
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("member", member);
            startActivity(intent);
            finish();
        } else {
            //회원로그인(LoginActivity2), 회원가입(JoinActivity)
            super.onBackPressed();
        }

    }
}
