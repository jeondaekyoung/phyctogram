package knowledge_seek.com.phyctogram;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.StoryProtocol;
import com.kakao.util.helper.TalkProtocol;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.phyctogram.SaveSharedPreference;
import knowledge_seek.com.phyctogram.retrofitapi.MemberAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.TimestampDes;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 로그인 페이지
 * 세션을 오픈한 후 action을 override해서 사용한다.
 */
public class LoginActivity extends BaseActivity {

    private SessionCallback callback;

    //레이아웃 정의
    private com.facebook.login.widget.LoginButton facebookLoginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private Button btn_login_kko;
    private Button btn_login;
    private Button btn_sitemap;         //테스트버튼 삭제해야함

    //데이터
    private Member memberActivity = new Member();

    /**
     * 로그인 버튼을 클릭 했을시 access token을 요청하도록 설정한다.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //카카오 로그인 세션 검사
        callback = new SessionCallback();
        //카카오 로그인이 되어있을 경우 메인화면으로 간다.
        Session.getCurrentSession().addCallback(callback);
        if(!Session.getCurrentSession().checkAndImplicitOpen()){
            Log.d("-진우-", "카카오 로그인 체크 안됨");
            //setContentView(R.layout.activity_login);
        } else {
            Log.d("-진우-", "카카오 로그인 완료 상태");
        }

        //페이스북 로그인 세션 검사
        if(AccessToken.getCurrentAccessToken() != null){
            Log.d("-진우-", "페이스북 로그인 완료 상태");
            // if the user already logged in, try to show the selection fragment
            Profile profile = Profile.getCurrentProfile();
            Log.d("-진우-", "로그인3 : " + profile.getId() + ", " + profile.getName() + ", " + profile.getLastName());

            Member member = new Member();
            member.setJoin_route("facebook");
            member.setFacebook_id(profile.getId());

            MemberAPI service = ServiceGenerator.createService(MemberAPI.class, "Member");
            Call<Member> call = service.findMemberByFacebookInfo(member);
            call.enqueue(new Callback<Member>() {
                @Override
                public void onResponse(Response<Member> response, Retrofit retrofit) {
                    Log.d("-진우-", "페이스북 가입 여부 결과1 : " + response.body());
                    memberActivity = (Member)response.body();
                    Log.d("-진우-", "페이스북 가입 여부 결과2 : " + memberActivity.toString());
                    redirectMainActivity(memberActivity);
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

        } else {
            Log.d("-진우-", "페이스북 로그인 체크 안됨");
        }

        //픽토그램 로그인 검사
        Log.d("-진우-", "픽토그램 로그인 확인 : " + SaveSharedPreference.getMemberSeq(getApplicationContext()));
        if(SaveSharedPreference.getMemberSeq(getApplicationContext()).length() == 0){
            Log.d("-진우-", "픽토그램 로그인 안됨");
        } else {
            //서버에서 멤버불러오기
            String member_seq = SaveSharedPreference.getMemberSeq(getApplicationContext());
            Log.d("-진우-", "픽토그램 로그인 됨 : " + member_seq);

            MemberAPI service = ServiceGenerator.createService(MemberAPI.class, "Member");
            Call<Member> call = service.findMemberByMemberSeq(Integer.valueOf(member_seq));
            call.enqueue(new Callback<Member>() {
                @Override
                public void onResponse(Response<Member> response, Retrofit retrofit) {
                    Log.d("-진우-", "픽토그램 가입 여부 성공 결과1 : " + response.body());
                    memberActivity = (Member) response.body();
                    Log.d("-진우-", "픽토그램 가입 여부 성공 결과2 : " + memberActivity.toString());
                    redirectMainActivity(memberActivity);
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

        }

        //페이스북 - This class can be extended to receive notifications of access token changes
        //액세스토큰이 변경시 실행
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.d("-진우-", "onCurrentAccessTokenChanged() 실행");
                if(currentAccessToken != null){
                    /*startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();*/
                } else {
                    setContentView(R.layout.activity_login);
                }
            }

        };

        //버튼 정의
        btn_login_kko = (Button)findViewById(R.id.btn_login_kko);
        btn_login_kko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "카카오 계정으로 로그인, LoginActivity");
                kakaoLogin();
            }
        });
        //Log.d("-진우-", " 아우 " + accessTokenTracker.isTracking());
        facebookLoginButton = (com.facebook.login.widget.LoginButton)findViewById(R.id.btn_login_fb);
        facebookLoginButton.setBackgroundResource(R.drawable.log_fb);
        facebookLoginButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        facebookLoginButton.setReadPermissions(Arrays.asList("user_photos", "email", "user_birthday", "user_friends"));
        //LoginResult : This class shows the results of a login operation.
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("-진우-", "페이스북 로그인 성공");
                //AccessToken : This class represents an immutable access token for using Facebook APIs. It also includes associated metadata such as expiration date and permissions.
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("-진우-", "페이스북 로그인1 : " + response.toString());
                        if (!"".equals(object)) {
                            String id = "";
                            String name = "";
                            String email = "";
                            String gender = "";
                            //String birthday = "";
                            try {
                                id = object.getString("id");
                                name = object.getString("name");
                                email = object.getString("email");
                                gender = object.getString("gender");
                                //birthday = object.getString("birthday");

                                Member member = new Member();
                                member.setFacebook_id(object.getString("id"));
                                member.setFacebook_name(object.getString("name"));
                                member.setFacebook_email(object.getString("email"));
                                member.setFacebook_gender(object.getString("gender"));
                                //member.setFacebook_birthday(object.getString("birthday"));
                                member.setJoin_route("facebook");

                                //Log.d("-진우-", "페이스북 로그인2 : " + id + ", " + name + ", " + email + ", " + gender + ", " + birthday);
                                Log.d("-진우-", "페이스북 로그인2 : " + id + ", " + name + ", " + email + ", " + gender);
                                Log.d("-진우-", "멤버는 " + member.toString());

                                registerMember(member);

                                //redirectMainActivity(memberActivity);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();


                //Profile profile = Profile.getCurrentProfile();
                //Log.d("-진우-", "로그인3 : " + profile.getId() + ", " + profile.getName() + ", " + profile.getLastName());

                //redirectMainActivity(memberActivity);
            }

            @Override
            public void onCancel() {
                Log.d("-진우-", "취소 ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("-진우-", "실패(에러) ");
            }
        });
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent memberlogin = new Intent(getApplicationContext(), LoginActivity2.class);
                startActivity(memberlogin);
            }
        });
        btn_sitemap = (Button)findViewById(R.id.btn_sitemap);
        btn_sitemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent memberlogin = new Intent(getApplicationContext(), sitemap.class);
                startActivity(memberlogin);
            }
        });
    }

    //유저저장
    private void registerMember(Member member){

        RegisterMemberTask task = new RegisterMemberTask();
        task.execute(member);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("-진우-", "LoginActivity.onActivityResult() " + requestCode + ", " + resultCode);
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
            Log.d("-진우-", "세션 없다. LoginActivity.onActivityResult()");
            return;
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //메인페이지로 이동
    private void redirectMainActivity(Member member) {
        //Log.d("-진우-", "페이스북 로그인 뒤에 멤버 : " + member.toString());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("member", member);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
        accessTokenTracker.stopTracking();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Call the 'activateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onResume methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.activateApp(this);

        /*if(AccessToken.getCurrentAccessToken() != null){
            // if the user already logged in, try to show the selection fragment
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Call the 'deactivateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onPause methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.deactivateApp(this);
    }


    //com.kakao.usermgmt.LoginButton
    private void kakaoLogin(){
        // 카톡 또는 카스가 존재하면 옵션을 보여주고, 존재하지 않으면 바로 직접 로그인창.
        final List<AuthType> authTypes = getAuthTyes();
        if(authTypes.size() == 1){
            Session.getCurrentSession().open(authTypes.get(0), self);
        } else {
            onClickLoginButton(authTypes);
        }
    }
    private List<AuthType> getAuthTyes(){
        final List<AuthType> availableAuthTypes = new ArrayList<AuthType>();
        if(TalkProtocol.existCapriLoginActivityInTalk(self, Session.getCurrentSession().isProjectLogin())){
            availableAuthTypes.add(AuthType.KAKAO_TALK);
            availableAuthTypes.add(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN);
        }
        if(StoryProtocol.existCapriLoginActivityInStory(self, Session.getCurrentSession().isProjectLogin())){
            availableAuthTypes.add(AuthType.KAKAO_STORY);
        }
        availableAuthTypes.add(AuthType.KAKAO_ACCOUNT);

        final AuthType[] selectedAuthTypes = Session.getCurrentSession().getAuthTypes();
        availableAuthTypes.retainAll(Arrays.asList(selectedAuthTypes));

        // 개발자가 설정한 것과 available 한 타입이 없다면 직접계정 입력이 뜨도록 한다.
        if(availableAuthTypes.size() == 0){
            availableAuthTypes.add(AuthType.KAKAO_ACCOUNT);
        }
        return availableAuthTypes;
    }
    private void onClickLoginButton(final List<AuthType> authTypes){
        final List<Item> itemList = new ArrayList<Item>();
        if(authTypes.contains(AuthType.KAKAO_TALK)) {
            itemList.add(new Item(R.string.com_kakao_kakaotalk_account, R.drawable.kakaotalk_icon, AuthType.KAKAO_TALK));
        }
        if(authTypes.contains(AuthType.KAKAO_STORY)) {
            itemList.add(new Item(R.string.com_kakao_kakaostory_account, R.drawable.kakaostory_icon, AuthType.KAKAO_STORY));
        }
        if(authTypes.contains(AuthType.KAKAO_ACCOUNT)){
            itemList.add(new Item(R.string.com_kakao_other_kakaoaccount, R.drawable.kakaoaccount_icon, AuthType.KAKAO_ACCOUNT));
        }
        itemList.add(new Item(R.string.com_kakao_account_cancel, 0, null)); //no icon for this one

        final Item[] items = itemList.toArray(new Item[itemList.size()]);

        final ListAdapter adapter = new ArrayAdapter<Item>(
                self,
                android.R.layout.select_dialog_item,
                android.R.id.text1, items){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView)v.findViewById(android.R.id.text1);

                tv.setText(items[position].textId);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(15);
                tv.setGravity(Gravity.CENTER);
                if(position == itemList.size() -1) {
                    tv.setBackgroundResource(R.drawable.kakao_cancel_button_background);
                } else {
                    tv.setBackgroundResource(R.drawable.kakao_account_button_background);
                }
                tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);

                int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);

                return v;
            }
        };
        new AlertDialog.Builder(self)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int position) {
                        final AuthType authType = items[position].authType;
                        if (authType != null) {
                            Session.getCurrentSession().open(authType, self);
                        }

                        dialog.dismiss();
                    }
                }).create().show();

    }
    private static class Item {
        public final int textId;
        public final int icon;
        public final AuthType authType;
        public Item(final int textId, final Integer icon, final AuthType authType) {
            this.textId = textId;
            this.icon = icon;
            this.authType = authType;
        }
    }
    //-> 여기까지



    // 카카오
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            Log.d("-진우-", "LoginActivity에서 SessionCallback.onSessionOpened() 실행");
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("-진우-", "LoginActivity에서 SessionCallback.onSessionOpenFailed() 실행");
            if(exception != null){
                Log.d("-진우 ", "에러 : " + exception.getMessage());
            }
            setContentView(R.layout.activity_login);
        }
    }

    //멤버 읽어오기
    private class RegisterMemberTask extends AsyncTask<Object, Void, Member> {

        private ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        private Member memberTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

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
                Log.d("-진우-", "페이스북 가입 성공 결과1 : " + member.toString());
                memberActivity = member;
                redirectMainActivity(memberActivity);
            } else {
                Log.d("-진우-", "페이스북 가입 정보 없음, LoginActivity");
            }
            //Log.d("-진우-", "페이스북 가입 성공 결과2 : " + memberActivity.toString());

            dialog.dismiss();
            super.onPostExecute(member);
        }
    }
}
