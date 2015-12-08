package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dkfka on 2015-11-25.
 */
public class UsersAddActivity extends BaseActivity {
    public static final String HTTPADDR = "http://117.52.89.181";

    //사이드매뉴
    public static Button bt_left;
    public static Button btn1;
    public static Button btn2;
    public static Button btn3;
    public static Button btn4;

    //데이터
    private Member member;
    private Users users;

    //레이아웃 정의
    private EditText et_name;
    private EditText et_initials;
    private DatePicker dp_lifedate;
    private RadioGroup rg_sexdstn;
    private RadioButton rb_female;
    private RadioButton rb_male;
    private Button btn_useradd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_add);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            member = (Member)bundle.getSerializable("member");
            Log.d("-진우-", "UserAaddActivity 에서 " + member.toString());
        }

        //사이드매뉴
        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        /*btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);*/

        //슬라이딩, 메뉴 BaseActivity.class
        initSildeMenu();

        users = new Users();

        //레이아웃 정의
        et_name = (EditText)findViewById(R.id.et_name);
        et_initials = (EditText)findViewById(R.id.et_initials);
        dp_lifedate = (DatePicker)findViewById(R.id.dp_lifedate);
        rg_sexdstn = (RadioGroup)findViewById(R.id.rg_sexdstn);
        rb_female = (RadioButton)findViewById(R.id.rb_female);
        rb_male = (RadioButton)findViewById(R.id.rb_male);
        btn_useradd = (Button)findViewById(R.id.btn_useradd);
        btn_useradd.setOnClickListener(new View.OnClickListener() {
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
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(HTTPADDR)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UsersAPI service = retrofit.create(UsersAPI.class);
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

    //users의 내용 체크
    private boolean checkUsers(Users users){
        //Log.d("-진우-", users.toString());
        if(users.getName().length() <= 0 || users.getInitials().length() <= 0){
            return false;
        }
        return true;
    }

}