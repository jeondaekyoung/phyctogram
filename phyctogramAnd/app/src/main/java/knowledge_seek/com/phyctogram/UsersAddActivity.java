package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

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
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //레이아웃 정의
    private EditText et_name;
    private DatePicker dp_lifedate;
    private RadioButton rb_female;
    private RadioButton rb_male;
    private Button btn_usersadd;
    private EditText et_initials;

    //데이터
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "UsersAddActivity.onCreate() 실행");

        //화면페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_users_add, ic_screen, true);

        //슬라이드 내 이미지, 셋팅
        img_profile = (CircularImageView) findViewById(R.id.img_profile);
        if (memberImg != null) {
            img_profile.setImageBitmap(memberImg);
        }

        //슬라이드 내 이름, 셋팅
        tv_member_name = (TextView) findViewById(R.id.tv_member_name);
        if (memberName != null) {
            tv_member_name.setText(memberName);
        }

        //슬라이드 내 아이 목록(ListView)에서 아이 선택시
        lv_usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*nowUsers = (Users)usersListSlideAdapter.getItem(position);
                Log.d("-진우-", "선택한 아이 : " + nowUsers.toString());
                Toast.makeText(getApplicationContext(), "'" + nowUsers.getName() + "' 아이를 선택하였습니다", Toast.LENGTH_LONG).show();*/

            }
        });

        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        //데이터셋팅
        users = new Users();

        //레이아웃 정의
        et_name = (EditText)findViewById(R.id.et_name);
        et_initials = (EditText)findViewById(R.id.et_initials);
        dp_lifedate = (DatePicker)findViewById(R.id.dp_lifedate);
        //rg_sexdstn = (RadioGroup)findViewById(R.id.rg_sexdstn);
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
                users.setMt(Utility.dateFormat(dp_lifedate.getMonth() + 1));
                users.setDe(Utility.dateFormat(dp_lifedate.getDayOfMonth()));
                if(rb_female.isChecked()){
                    users.setSexdstn("female");
                } else if(rb_male.isChecked()){
                    users.setSexdstn("male");
                }

                if(!checkUsers(users)){
                    return ;
                }

                //내아이 저장하기 - 비동기
                Log.d("-진우-", "내 아이 저장하기 : " + users.toString());
                Log.d("-진우-", "json : " + Utility.users2json(users));
                UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
                Call<String> call = service.registerUsers(users);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        Log.d("-진우-", "내 아이 등록 결과 : " + response.body());
                        if(response.body() != null && response.body().equals("success")){
                            Toast.makeText(getApplicationContext(), "내 아이 등록에 성공하였습니다.", Toast.LENGTH_LONG).show();
                            /*Intent intent = new Intent(getApplicationContext(), UsersManageActivity.class);
                            intent.putExtra("member", member);
                            startActivity(intent);
                            finish();*/
                            /*FindUsersTask task = new FindUsersTask();
                            task.execute();*/
                            onBackPressed();
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
        Log.d("-진우-", "UsersAddActivity.onResume() 실행");

        //슬라이드메뉴 셋팅
        initSildeMenu();

        //슬라이드메뉴 내 아이 목록 셋팅
        usersListSlideAdapter.setUsersList(usersList);
        usersListSlideAdapter.setSelectUsers(nowUsers.getUser_seq());
        int height = getListViewHeight(lv_usersList);
        lv_usersList.getLayoutParams().height = height;
        usersListSlideAdapter.notifyDataSetChanged();

        //슬라이드메뉴 셋팅(내 아이 목록, 계정이미지)
        //UsersAddTask task = new UsersAddTask();
        //task.execute(img_profile);

        Log.d("-진우-", "UsersAddActivity 에 onResume() : " + member.toString());

        Log.d("-진우-", "UsersAddActivity.onResume() 끝");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //users의 내용 체크
    private boolean checkUsers(Users users){
        //Log.d("-진우-", users.toString());
        if(users.getName().length() <= 0){
            Toast.makeText(getApplicationContext(), "이름을 확인해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(users.getInitials().length() <= 0 || users.getInitials().length() >= 4) {
            Toast.makeText(getApplicationContext(), "이니셜을 확인해주세요", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //Toast.makeText(getApplicationContext(), users.getInitials() + " : " + users.getInitials().matches("^[A-Z0-9]*$"), Toast.LENGTH_SHORT).show();
            if(!users.getInitials().matches("^[A-Z0-9]*$")){
                Toast.makeText(getApplicationContext(), "이니셜을 확인해주세요", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    //내아이추가페이지 초기 데이터조회(슬라이드 내 아이 목록, 내 이미지)
    private class UsersAddTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(UsersAddActivity.this);
        private List<Users> usersTask;
        private CircularImageView img_profileTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            Bitmap mBitmap = null;
            img_profileTask = (CircularImageView) params[0];

            //슬라이드메뉴에 있는 내 아이 목록
            /*UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Users");
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }*/

            //이미지 불러오기
            /*String image_url = null;
            if (member.getJoin_route().equals("kakao")) {
                image_url = member.getKakao_thumbnailimagepath();
                InputStream in = null;
                try {
                    Log.d("-진우-", "이미지 주소 : " + image_url);
                    in = new URL(image_url).openStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (member.getJoin_route().equals("facebook")) {
                image_url = "http://graph.facebook.com/" + member.getFacebook_id() + "/picture?type=large";
                //이미지 불러오기
                InputStream in = null;
                try {
                    //페이스북은 jpg파일이 링크 걸린 것이 아니다.
                    //http://graph.facebook.com/userid/picture?type=large
                    Log.d("-진우-", "이미지 주소 : " + image_url);

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(image_url)
                            .build();
                    com.squareup.okhttp.Response response = client.newCall(request).execute();
                    in = response.body().byteStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            /*if (bitmap != null) {
                Log.d("-진우-", "이미지읽어옴");
                img_profileTask.setImageBitmap(bitmap);
            }

            if (usersTask != null && usersTask.size() > 0) {
                Log.d("-진우-", "내 아이는 몇명? " + usersTask.size());
                for (Users u : usersTask) {
                    Log.d("-진우-", "내 아이 : " + u.toString());
                }
                usersList = usersTask;

                usersListSlideAdapter.setUsersList(usersList);
                if (nowUsers == null) {
                    nowUsers = usersTask.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();*/

            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }

    //내 아이 목록 읽어오기
    private class FindUsersTask extends AsyncTask<Void, Void, Void>{

        private ProgressDialog dialog = new ProgressDialog(UsersAddActivity.this);
        private List<Users> usersTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기디려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            //슬라이드메뉴에 있는 내 아이 목록
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Users");
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(usersTask != null && usersTask.size() > 0){
                Log.d("-진우-", "내 아이는 몇명? " + usersTask.size());
                for(Users u : usersTask) {
                    Log.d("-진우-", "내 아이 : " + u.toString());
                }
                usersList = usersTask;
                usersListSlideAdapter.setUsersList(usersList);
                /*if (nowUsers == null) {
                    nowUsers = usersTask.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());*/
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }
            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }
}