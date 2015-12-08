package knowledge_seek.com.phyctogram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.phyctogram.SaveSharedPreference;
import knowledge_seek.com.phyctogram.retrofitapi.MemberAPI;
import knowledge_seek.com.phyctogram.retrofitapi.TimestampDes;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dkfka on 2015-11-24.
 */
public class LoginActivity2 extends Activity {
    public static final String HTTPADDR = "http://117.52.89.181";
    private Member memberActivity;
    private Member member;

    //정의
    private EditText et_email;
    private EditText et_pw;
    private Button btn_member_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        member = new Member();
        et_email = (EditText)findViewById(R.id.et_email);
        et_pw = (EditText)findViewById(R.id.et_pw);
        btn_member_login = (Button)findViewById(R.id.btn_member_login);

        //로그인
        btn_member_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setEmail(et_email.getText().toString());
                member.setPassword(et_pw.getText().toString());

                //멤버 내용 체크
                if(!checkMember(member)){
                    return;
                }

                //멤버 로그인
                member.setJoin_route("phyctogram");
                Log.d("-진우-", member.toString());
                Log.d("-진우-", Utility.member2json(member));
                loginMember(member);
            }
        });

    }



    //멤버 내용 체크
    private boolean checkMember(Member member){
        if(member.getEmail().length() <= 0 || member.getPassword().length() <= 0){
            Toast.makeText(getApplicationContext(), "내용을 확인해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //멤버로그인
    private void loginMember(Member member){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampDes());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPADDR)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MemberAPI service = retrofit.create(MemberAPI.class);
        Call<Member> call = service.loginMemberByPhycto(member);
        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Response<Member> response, Retrofit retrofit) {
                Log.d("-진우-", "로그인 성공 결과1 : " + response.body());
                memberActivity = (Member)response.body();
                if(memberActivity == null){
                    Toast.makeText(getApplicationContext(), "이메일과 패스워드를 확인해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    //가입완료후 로그인유지를 위해 preference를 사용한다.
                    SaveSharedPreference.setMemberSeq(getApplicationContext(), String.valueOf(memberActivity.getMember_seq()));
                    redirectMainActivity(memberActivity);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("-진우-", "로그인하는데 실패하였습니다. - " + t.getMessage() + ", " + t.getCause() + ", " + t.getStackTrace());
            }
        });

    }


    private void redirectMainActivity(Member member) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("member", member);
        startActivity(intent);
        finish();
    }

}