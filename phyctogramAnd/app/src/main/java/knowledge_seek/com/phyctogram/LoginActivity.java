package knowledge_seek.com.phyctogram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.StoryProtocol;
import com.kakao.util.helper.TalkProtocol;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * 로그인 페이지
 * 세션을 오픈한 후 action을 override해서 사용한다.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private SessionCallback callback;

    private com.facebook.login.widget.LoginButton facebookLoginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private boolean isResumed = false;

    private Button logoutButton;



    /**
     * 로그인 버튼을 클릭 했을시 access token을 요청하도록 설정한다.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //카카오 로그인 세션 검사
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        if(!Session.getCurrentSession().checkAndImplicitOpen()){
            setContentView(R.layout.activity_login);
        }

        //페이스북 로그인 세션 검사
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken != null){
                    startActivity(new Intent(getApplicationContext(), Maintestactivity.class));
                    finish();
                } else {
                    //
                }
            }
        };


        facebookLoginButton = (com.facebook.login.widget.LoginButton)findViewById(R.id.btn_login_fb);
        facebookLoginButton.setBackgroundResource(R.drawable.log_fb);
        facebookLoginButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        facebookLoginButton.setReadPermissions(Arrays.asList("user_photos", "email", "user_birthday", "user_friends"));
        //LoginResult : This class shows the results of a login operation.
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("-진우-", "로그인 성공");
                //AccessToken : This class represents an immutable access token for using Facebook APIs. It also includes associated metadata such as expiration date and permissions.
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback(){
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("-진우-", "로그인1 : " + response.toString());
                        if (!"".equals(object)) {
                            String id = "";
                            String name = "";
                            String email = "";
                            String gender = "";
                            String birthday = "";
                            try {
                                id = object.getString("id");
                                name = object.getString("name");
                                email = object.getString("email");
                                gender = object.getString("gender");
                                birthday = object.getString("birthday");

                                Log.d("-진우-", "로그인2 : " + id + ", " + name + ", " + email + ", " + gender + ", " + birthday);
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


                Profile profile = Profile.getCurrentProfile();
                Log.d("-진우-", "로그인3 : " + profile.getId() + ", " + profile.getName() + ", " + profile.getLastName());

                startActivity(new Intent(getApplicationContext(), Maintestactivity.class));
                finish();
            }

            @Override
            public void onCancel() {
                Log.d("-진우-", "취소 ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("-진우-", "실패 : " + error.getCause().toString());
            }
        });


        //로그아웃
        logoutButton = (Button)findViewById(R.id.logout_Button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "페이스북 로그아웃");
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                //accessToken 값이 있다면 로그인 상태라고 판단
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("-진우-", "LoginActivity.onActivityResult() " + requestCode + ", " + resultCode);
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
            Log.d("-진우-", "세션 없다.");
            return;
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
        accessTokenTracker.stopTracking();
    }

    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login:
                Log.d("-진우-", "픽토그램 로그인");
                Intent memberlogin = new Intent(this, sitemap.class);
                startActivity(memberlogin);
                break;

            //case R.id.com_kakao_login:
            case R.id.btn_login_kko:
                Log.d("-진우-", "카카오 계정으로 로그인");

                kakaoLogin();

                break;

            /*case R.id.btn_login_fb:
                Log.d("-진우-", "페이스북 로그인");
                break;*/
        }

    }

    @Override
    protected void onResume() {
        super.onResume();


        // Call the 'activateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onResume methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.activateApp(this);

        if(AccessToken.getCurrentAccessToken() != null){
            // if the user already logged in, try to show the selection fragment
            startActivity(new Intent(getApplicationContext(), Maintestactivity.class));
            finish();
        }
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




    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            Log.d("-진우-", "LoginActivity에서 SessionCallback.onSessionOpened() 실행");
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null){
                Log.d("-진우 ", "에러 : " + exception.getMessage());
            }

            setContentView(R.layout.activity_login);
        }
    }
}
