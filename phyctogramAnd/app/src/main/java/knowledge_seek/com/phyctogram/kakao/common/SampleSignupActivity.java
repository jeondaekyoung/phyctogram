package knowledge_seek.com.phyctogram.kakao.common;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import java.io.IOException;
import java.sql.Timestamp;

import knowledge_seek.com.phyctogram.MainActivity;
import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.retrofitapi.MemberAPI;
import knowledge_seek.com.phyctogram.retrofitapi.MemberDes;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.TimestampDes;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 유효한 세션이 있다는 검증 후
 * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
 * Created by sjw on 2015-11-26.
 */
public class SampleSignupActivity extends BaseActivity {
    public static final String HTTPADDR = "http://www.phyctogram.com";

    private Member memberActivity = new Member();


    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }

    protected void showSignup(){
        Log.d("-진우 ", "추가로 받고자 하는 사용자 정보를 나타내는 layout");
    }

    /**
     * 사용자 정보 요청(https://developers.kakao.com/docs/android#사용자-관리-사용자-정보-요청)
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Log.d("-진우 ", message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    Toast.makeText(getApplicationContext(), "Unstable network connection. Please try again later.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("-진우-", "requestMe.onSessionClosed()");
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                Log.d("-진우-", "requestMe.onNotSignedUp()");
                showSignup();
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.d("-진우 ", "SampleSignupActivity.UserProfile : " + userProfile);
                Member member = new Member();
                member.setKakao_id(String.valueOf(userProfile.getId()));
                member.setKakao_nickname(userProfile.getNickname());
                member.setKakao_thumbnailimagepath(userProfile.getThumbnailImagePath());
                member.setJoin_route("kakao");
                Log.d("-진우-", member.toString());
                //Log.d("-진우-", "json : " + Utility.member2json(member));
                registerMember(member);
                //redirectMainActivity(memberActivity);
            }
        });
    }

    //유저저장
    private void registerMember(Member member){
        //Member m = new Member();

        /*GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampDes());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPADDR)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MemberAPI service = retrofit.create(MemberAPI.class);
        Call<Member> call = service.registerMember(member);
        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Response<Member> response, Retrofit retrofit) {
                Log.d("-진우-", "카카오 가입 성공 결과1 : " + response.body());
                memberActivity = (Member)response.body();
                Log.d("-진우-", "성공 결과2 : " + memberActivity.toString());
                redirectMainActivity(memberActivity);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("-진우-", "카카오 가입 member를 저장하는데 실패하였습니다. - " + t.getMessage() + ", " + t.getCause() + ", " + t.getStackTrace());
            }
        });*/


        RegisterMemberTask task = new RegisterMemberTask();
        task.execute(member);


    }

    private void redirectMainActivity(Member member) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("member", member);
        startActivity(intent);
        finish();
    }

    //멤버 읽어오기
    private class RegisterMemberTask extends AsyncTask<Object, Void, Member>{

        private Member memberTask;

        @Override
        protected Member doInBackground(Object... params) {
            Member member = null;
            memberTask = (Member)params[0];

            MemberAPI service = ServiceGenerator.createService(MemberAPI.class, "Member");
            Call<Member> call = service.registerMember(memberTask);
            try {
                member = call.execute().body();
            } catch (IOException e){
                e.printStackTrace();
            }
            return member;
        }

        @Override
        protected void onPostExecute(Member member) {
            if(member != null) {
                Log.d("-진우-", "카카오 가입 성공 결과1 : " + member.toString());
                memberActivity = member;
            } else {
                Log.d("-진우-", "카카오 가입 정보 없음");
            }
            Log.d("-진우-", "카카오 가입 성공 결과2 : " + memberActivity.toString());
            redirectMainActivity(memberActivity);

            super.onPostExecute(member);
        }
    }

}
