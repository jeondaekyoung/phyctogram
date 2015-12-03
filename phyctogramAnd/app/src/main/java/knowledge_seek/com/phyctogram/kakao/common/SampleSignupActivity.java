package knowledge_seek.com.phyctogram.kakao.common;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import knowledge_seek.com.phyctogram.Maintestactivity;
import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.retrofitapi.MemberAPI;
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
    public static final String HTTPADDR = "http://117.52.89.181";
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
                Log.d("-진우 ", "UserProfile : " + userProfile);
                Member member = new Member();
                member.setKakao_id(String.valueOf(userProfile.getId()));
                member.setKakao_nickname(userProfile.getNickname());
                member.setKakao_thumbnailimagepath(userProfile.getThumbnailImagePath());
                member.setJoin_route("kakao");
                Log.d("-진우-", member.toString());
                registerMember(member);
                redirectMainActivity();
            }
        });
    }

    //유저저장
    private void registerMember(Member member){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPADDR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MemberAPI service = retrofit.create(MemberAPI.class);
        Call<String> call = service.registerMember(member);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Log.d("-진우-", "성공 결과 : " + response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("-진우-", "member를 저장하는데 실패하였습니다. - " + t.getMessage() + ", " + t.getCause() + ", " + t.getStackTrace());
            }
        });

    }

    private void redirectMainActivity() {
        startActivity(new Intent(this, Maintestactivity.class));
        finish();
    }

}
