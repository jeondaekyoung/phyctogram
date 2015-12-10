package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
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
public class UsersModActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private Button btn_left;
    private LinearLayout ic_screen;

    //데이터
    private Users users = null;

    //레이아웃 정의
    private EditText et_name;
    private EditText et_initials;
    private DatePicker dp_lifedate;
    private RadioGroup rg_sexdstn;
    private RadioButton rb_female;
    private RadioButton rb_male;
    private Button btn_usersmod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_users_mod, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();


        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            users = (Users) bundle.getSerializable("users");
            Log.d("-진우-", "UsersModActivity 에서 " + users.toString());
        } else {
            Log.d("-진우-", "UsersModActivity 에 users가 없다.");
        }


        //사이드 메뉴
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });


        //레이아웃 정의
        et_name = (EditText)findViewById(R.id.et_name);
        et_initials = (EditText)findViewById(R.id.et_initials);
        dp_lifedate = (DatePicker)findViewById(R.id.dp_lifedate);
        rg_sexdstn = (RadioGroup)findViewById(R.id.rg_sexdstn);
        rb_female = (RadioButton)findViewById(R.id.rb_female);
        rb_male = (RadioButton)findViewById(R.id.rb_male);
        btn_usersmod = (Button)findViewById(R.id.btn_usersmod);
        btn_usersmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.setName(et_name.getText().toString());
                users.setInitials(et_initials.getText().toString());
                users.setLifyea(String.valueOf(dp_lifedate.getYear()));
                users.setMt(String.valueOf((dp_lifedate.getMonth() + 1)));
                users.setDe(String.valueOf(dp_lifedate.getDayOfMonth()));
                if (rb_female.isChecked()) {
                    users.setSexdstn("female");
                } else if (rb_male.isChecked()) {
                    users.setSexdstn("male");
                }

                if (!checkUsers(users)) {
                    return;
                }

                Log.d("-진우-", "내 아이 수정하기 : " + users.toString());
                Log.d("-진우-", "json : " + Utility.users2json(users));
                UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
                Call<String> call = service.modUsersByUsers(users);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        Log.d("-진우-", "내 아이 수정 성공 결과 : " + response.body());
                        if (response.body().equals("success")) {
                            Toast.makeText(UsersModActivity.this, "수정하였습니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("-진우-", "내 아이 수정 실패 " + t.getMessage() + ", " + t.getCause() + ", " + t.getStackTrace());
                    }
                });
            }
        });

        //화면에 데이터 셋팅
        if(users != null){
            et_name.setText(users.getName());
            et_initials.setText(users.getInitials());
            dp_lifedate.updateDate(Integer.valueOf(users.getLifyea()), Integer.valueOf(users.getMt()) - 1, Integer.valueOf(users.getDe()));
            if(users.getSexdstn().equals("male")){
                rb_male.setChecked(true);
                rb_female.setChecked(false);
            } else {
                rb_female.setChecked(true);
                rb_male.setChecked(false);
            }
        }


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
