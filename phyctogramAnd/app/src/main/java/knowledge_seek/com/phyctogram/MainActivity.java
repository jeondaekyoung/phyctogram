package knowledge_seek.com.phyctogram;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.SqlCommntyListView;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.SqlCommntyListViewAPI;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;

/**
 * Created by dkfka on 2015-11-25.
 */
public class MainActivity extends BaseActivity {

    //데이터정의

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;           //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //레이아웃정의
    private ImageButton btn_record;                     //기록조회
    private ImageButton imBtn_community_list;      //수다방 리스트
    private TextView tv_users_name;                 //아이 이름 출력
    private ImageButton btn_users_analysis;     //분석리포트


    private TextView tv_popularTop1_title;          //커뮤니티(수다방) 인기 Top3
    private TextView tv_popularTop1_name;
    private TextView tv_popularTop2_title;
    private TextView tv_popularTop2_name;
    private TextView tv_popularTop3_title;
    private TextView tv_popularTop3_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "MainActivity.onCreate() 실행");

        //화면 페이지
        ic_screen = (LinearLayout) findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_main, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //슬라이드 내 이미지
        img_profile = (CircularImageView)findViewById(R.id.img_profile);
        //슬라이드 내 이름
        tv_member_name = (TextView)findViewById(R.id.tv_member_name);
        //슬라이드 내 아이 목록(ListView)에서 아이 선택시
        tv_users_name = (TextView) findViewById(R.id.tv_users_name);
        lv_usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nowUsers = (Users) usersListSlideAdapter.getItem(position);
                Log.d("-진우-", "선택한 아이 : " + nowUsers.toString());
                Toast.makeText(getApplicationContext(), "'" + nowUsers.getName() + "' 아이를 선택하였습니다", Toast.LENGTH_LONG).show();

                if (tv_users_name != null) {
                    tv_users_name.setText(nowUsers.getName());
                }

                //메인페이지에 출력할 아이에 관한 데이터(분석포함)를 가져와야한다.
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

        //기록조회
        btn_record = (ImageButton) findViewById(R.id.btn_record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
            }
        });
        //분석리포트
        btn_users_analysis = (ImageButton)findViewById(R.id.btn_users_analysis);
        btn_users_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usersList == null || usersList.size() <= 0){
                    Toast.makeText(getApplicationContext(), "내 아이 관리에서 아이를 등록해주세요", Toast.LENGTH_LONG).show();
                    return ;
                }
                Intent intent = new Intent(getApplicationContext(), UsersAnalysisActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
            }
        });
        //수다방(community)
        imBtn_community_list = (ImageButton) findViewById(R.id.imBtn_community_list);
        imBtn_community_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CommunityListActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
            }
        });
        //커뮤니티(수다방) 인기 Top3
        tv_popularTop1_title = (TextView)findViewById(R.id.tv_popularTop1_title);
        tv_popularTop1_name = (TextView)findViewById(R.id.tv_popularTop1_name);
        tv_popularTop2_title = (TextView)findViewById(R.id.tv_popularTop2_title);
        tv_popularTop2_name = (TextView)findViewById(R.id.tv_popularTop2_name);
        tv_popularTop3_title = (TextView)findViewById(R.id.tv_popularTop3_title);
        tv_popularTop3_name = (TextView)findViewById(R.id.tv_popularTop3_name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "MainActivity.onResume() 실행");

        //슬라이드메뉴 셋팅(내 아이 목록, 계정이미지, 수다방인기Top3)
        MainDataTask task = new MainDataTask();
        task.execute(img_profile);

        Log.d("-진우-", "MainActivity 에 onResume() : " + member.toString());
        String name = null;
        if(member.getJoin_route().equals("kakao")){
            name = member.getKakao_nickname() + " 님";
        } else if(member.getJoin_route().equals("facebook")){
            name = member.getFacebook_name() + " 님";
        } else {
            name = member.getName() + " 님";
        }
        if(name != null){
            tv_member_name.setText(name);
        }
        Log.d("-진우-", "MainActivity.onResume() 끝");
    }

    //메인페이지 초기 데이터조회(슬라이드 내 아이 목록, 계정이미지, 수다방인기Top3)
    //해야할 일 : 메인페이지에 출력할 아이에 관한 데이터(분석포함)를 가져와야한다.
    private class MainDataTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        private List<Users> usersTask;
        private CircularImageView img_profileTask;
        private List<SqlCommntyListView> sqlCommntyListViewTask = null;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap mBitmap = null;
            img_profileTask = (CircularImageView) objects[0];

            //슬라이드메뉴에 있는 내 아이 목록
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }


            String image_url = null;
            if (member.getJoin_route().equals("kakao")) {
                image_url = member.getKakao_thumbnailimagepath();
                //이미지 불러오기
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
                    Response response = client.newCall(request).execute();
                    in = response.body().byteStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //커뮤니티(수다방) 인기 Top3 읽어오기
            SqlCommntyListViewAPI service1 = ServiceGenerator.createService(SqlCommntyListViewAPI.class, "SqlCommntyListView");
            Call<List<SqlCommntyListView>> call1 = service1.findCommntyPopularTop3();
            try {
                List<SqlCommntyListView> resultList1 = call1.execute().body();
                if (resultList1 != null) {
                    for (SqlCommntyListView s : resultList1) {
                        Log.d("-진우-", "인기 Top3 : " + s.toString());
                    }
                    sqlCommntyListViewTask = resultList1;

                }
            } catch (IOException e) {
                Log.d("-진우-", "수다방 목록 조회 실패");
            }

            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                Log.d("-진우-", "이미지 읽어옴");
                img_profileTask.setImageBitmap(bitmap);
            }
            if (usersTask != null && usersTask.size() > 0) {
                Log.d("-진우-", "내 아이는 몇명? " + usersTask.size());
                for (Users u : usersTask) {
                    Log.d("-진우-", "내아이 : " + u.toString());
                }
                usersList = usersTask;

                usersListSlideAdapter.setUsersList(usersList);
                if (nowUsers == null) {
                    nowUsers = usersTask.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());
                tv_users_name.setText(nowUsers.getName());
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();

            //커뮤니티(수다방) 인기 Top3 셋팅
            if (sqlCommntyListViewTask != null && sqlCommntyListViewTask.size() >= 3) {
                tv_popularTop1_title.setText(sqlCommntyListViewTask.get(0).getTitle());
                tv_popularTop1_name.setText(sqlCommntyListViewTask.get(0).getName());
                tv_popularTop2_title.setText(sqlCommntyListViewTask.get(1).getTitle());
                tv_popularTop2_name.setText(sqlCommntyListViewTask.get(1).getName());
                tv_popularTop3_title.setText(sqlCommntyListViewTask.get(2).getTitle());
                tv_popularTop3_name.setText(sqlCommntyListViewTask.get(2).getName());
            } else if (sqlCommntyListViewTask != null && sqlCommntyListViewTask.size() == 2) {
                tv_popularTop1_title.setText(sqlCommntyListViewTask.get(0).getTitle());
                tv_popularTop1_name.setText(sqlCommntyListViewTask.get(0).getName());
                tv_popularTop2_title.setText(sqlCommntyListViewTask.get(1).getTitle());
                tv_popularTop2_name.setText(sqlCommntyListViewTask.get(1).getName());
                tv_popularTop3_title.setText(null);
                tv_popularTop3_name.setText(null);
            } else if (sqlCommntyListViewTask != null && sqlCommntyListViewTask.size() == 1) {
                tv_popularTop1_title.setText(sqlCommntyListViewTask.get(0).getTitle());
                tv_popularTop1_name.setText(sqlCommntyListViewTask.get(0).getName());
                tv_popularTop2_title.setText(null);
                tv_popularTop2_name.setText(null);
                tv_popularTop3_title.setText(null);
                tv_popularTop3_name.setText(null);
            } else {
                tv_popularTop1_title.setText(null);
                tv_popularTop1_name.setText(null);
                tv_popularTop2_title.setText(null);
                tv_popularTop2_name.setText(null);
                tv_popularTop3_title.setText(null);
                tv_popularTop3_name.setText(null);
            }

            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }


}