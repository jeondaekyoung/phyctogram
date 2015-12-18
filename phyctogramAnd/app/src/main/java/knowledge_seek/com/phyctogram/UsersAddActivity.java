package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dkfka on 2015-11-25.
 */
public class UsersAddActivity extends BaseActivity {

    //레이아웃 - 슬라이드 메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;


    //데이터
    //private Member member;
    private Users users;

    //레이아웃 정의
    private EditText et_name;
    private EditText et_initials;
    private DatePicker dp_lifedate;
    private RadioGroup rg_sexdstn;
    private RadioButton rb_female;
    private RadioButton rb_male;
    private Button btn_usersadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_users_add, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });


        //슬라이딩, 메뉴 BaseActivity.class
        initSildeMenu();


        //데이터셋팅
        users = new Users();

        //레이아웃 정의
        et_name = (EditText)findViewById(R.id.et_name);
        et_initials = (EditText)findViewById(R.id.et_initials);
        dp_lifedate = (DatePicker)findViewById(R.id.dp_lifedate);
        rg_sexdstn = (RadioGroup)findViewById(R.id.rg_sexdstn);
        rb_female = (RadioButton)findViewById(R.id.rb_female);
        rb_male = (RadioButton)findViewById(R.id.rb_male);
        btn_usersadd = (Button)findViewById(R.id.btn_usersadd);
        btn_usersadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.d("-진우-", dp_lifedate.getYear() + "/" + (dp_lifedate.getMonth()+1) + "/" + dp_lifedate.getDayOfMonth());
                Log.d("-진우-", "#" + et_name.getText().toString() + "#" + " #" + et_initials.getText().toString() + "#");
                Log.d("-진우-", String.valueOf(rb_female.isChecked()));
                Log.d("-진우-", String.valueOf(rb_male.isChecked()));*/

                //이름 및 이니셜 체크
                users.setMember_seq(member.getMember_seq());
                users.setName(et_name.getText().toString());
                users.setInitials(et_initials.getText().toString());
                users.setLifyea(String.valueOf(dp_lifedate.getYear()));
                users.setMt(String.valueOf((dp_lifedate.getMonth() + 1)));
                users.setDe(String.valueOf(dp_lifedate.getDayOfMonth()));
                if(rb_female.isChecked()){
                    users.setSexdstn("female");
                } else if(rb_male.isChecked()){
                    users.setSexdstn("male");
                }

                if(!checkUsers(users)){
                    return ;
                }

                //내아이 저장하기
                Log.d("-진우-", "내 아이 저장하기 : " + users.toString());
                Log.d("-진우-", "json : " + Utility.users2json(users));
                UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
                Call<String> call = service.registerUsers(users);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        Log.d("-진우-", "내 아이 등록 결과 : " + response.body());
                        if(response.body() != null && response.body().equals("success")){
                            Toast.makeText(getApplicationContext(), "내 아이 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("-진우-", "내 아이 등록 실패 " + t.getMessage() + ", " + t.getCause() + ", " + t.getStackTrace());
                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        //슬라이드메뉴에 있는 내 아이 목록
        updateScreenSlide();
        Log.d("-진우-", "UsersAddActivity 에 onResume() : " + member.toString());
    }

    //users의 내용 체크
    private boolean checkUsers(Users users){
        //Log.d("-진우-", users.toString());
        if(users.getName().length() <= 0 || users.getInitials().length() <= 0){
            return false;
        }
        return true;
    }

}