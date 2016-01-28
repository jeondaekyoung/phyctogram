package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Commnty;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.CommntyAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;

/**
 * Created by dkfka on 2015-11-26.
 */
public class CommunityWriteActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //레이아웃정의
    private Button btn_community_save;
    private EditText et_title;
    private EditText et_contents;

    //데이터정의
    private Commnty commnty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_commuinity_write, ic_screen, true);


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
                /*nowUsers = (Users) usersListSlideAdapter.getItem(position);
                Log.d("-진우-", "선택한 아이 : " + nowUsers.toString());
                Toast.makeText(getApplicationContext(), "'" + nowUsers.getName() + "' 아이를 선택하였습니다", Toast.LENGTH_LONG).show();*/

            }
        });

        //레이아웃 정의
        btn_left = (ImageButton)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        et_title = (EditText)findViewById(R.id.et_title);
        et_contents = (EditText)findViewById(R.id.et_contents);
        //수다방(커뮤니티) 글 저장
        btn_community_save = (Button)findViewById(R.id.btn_community_save);
        btn_community_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "저장하기");
                commnty = new Commnty();
                commnty.setTitle(et_title.getText().toString());
                commnty.setContents(et_contents.getText().toString());
                commnty.setMember_seq(member.getMember_seq());

                if(!checkCommnty(commnty)){
                    return;
                }
                Log.d("-진우-", commnty.toString());
                RegisterCommntyTask task = new RegisterCommntyTask(commnty);
                task.execute();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "CommunityWriteActivity.onResume() 실행");

        //슬라이드메뉴 셋팅
        initSildeMenu();

        //슬라이드메뉴 내 아이 목록 셋팅
        usersListSlideAdapter.setUsersList(usersList);
        int height = getListViewHeight(lv_usersList);
        lv_usersList.getLayoutParams().height = height;
        usersListSlideAdapter.notifyDataSetChanged();

        //슬라이드메뉴 셋팅(내 아이 목록, 계정이미지)
        /*CommunityWriteTask task = new CommunityWriteTask();
        task.execute(img_profile);*/

        Log.d("-진우-", "CommunityWriteActivity 에 onResume() : " + member.toString());

        Log.d("-진우-", "CommunityWriteActivity.onResume() 끝");
    }

    //Commnty의 내용체크
    private boolean checkCommnty(Commnty commnty){
        if(commnty.getTitle().length() <= 0 || commnty.getContents().length() <= 0){
            Toast.makeText(getApplicationContext(), "제목과 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //글 저장
    private class RegisterCommntyTask extends AsyncTask<Void, Void, String> {

        private Commnty commnty;
        private ProgressDialog dialog = new ProgressDialog(CommunityWriteActivity.this);

        public RegisterCommntyTask(Commnty commnty) {
            this.commnty = commnty;
        }

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            Log.d("-진우-", Utility.commnty2json(commnty));
            String result = null;
            CommntyAPI service = ServiceGenerator.createService(CommntyAPI.class, "CommunityWriteActivity");
            Call<String> call = service.registerCommnty(commnty);
            try {
                result = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "글 저장 실패");
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result != null && result.equals("success")){
                Toast.makeText(getApplicationContext(), "저장하였습니다", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("-진우-", "저장하는데 실패하였습니다");
            }

            dialog.dismiss();
            super.onPostExecute(result);
        }
    }

    //수다방글쓰기페이지 초기 데이터조회(슬라이드 내 아이 목록, 내 이미지)
    private class CommunityWriteTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(CommunityWriteActivity.this);
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
}