package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;

/**
 * Created by dkfka on 2015-11-27.
 */
public class WithdrawActivity extends BaseActivity {

    //데이터정의

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //레이아웃정의
    private TextView tv_join_route;     //가입경로
    private TextView tv_name;           //이름
    private EditText et_pw;
    private EditText et_pw1;
    private Button btn_withdraw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout) findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_withdraw, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //슬라이드 내 이미지
        img_profile = (CircularImageView) findViewById(R.id.img_profile);
        //슬라이드 내 이름
        tv_member_name = (TextView) findViewById(R.id.tv_member_name);

        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        tv_join_route = (TextView) findViewById(R.id.tv_join_route);
        tv_name = (TextView) findViewById(R.id.tv_name);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_pw1 = (EditText) findViewById(R.id.et_pw1);
        btn_withdraw = (Button) findViewById(R.id.btn_withdraw);
        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pw = et_pw.getText().toString();
                String pw1 = et_pw1.getText().toString();

                if (!checkPW(pw, pw1)) {
                    return;
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(WithdrawActivity.this);
                dialog.setTitle("탈퇴하기")
                        .setMessage("회원 탈퇴시 모든 데이터가 삭제되므로 복구가 되지 않습니다. 탈퇴 하시겠습니까?")
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Log.d("-진우-", "탈퇴하기 : " + member.getMember_seq() + ", " + pw);


                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "WithdrawActivity.onResume() 실행");

        //슬라이드메뉴 셋팅(내 아이목록, 계정이름, 계정이미지)
        WithdrawTask task = new WithdrawTask();
        task.execute(img_profile);

        Log.d("-진우-", "WithdrawActivity 에서 onResume() : " + member.toString());

        String name = null;
        if (member.getJoin_route().equals("kakao")) {
            name = member.getKakao_nickname() + " 님";
            tv_join_route.setText("카카오");
            tv_name.setText(member.getKakao_nickname());
        } else if (member.getJoin_route().equals("facebook")) {
            name = member.getFacebook_name() + " 님";
            tv_join_route.setText("페이스북");
            tv_name.setText(member.getFacebook_name());
        } else {
            name = member.getName() + " 님";
            tv_join_route.setText("픽토그램");
            tv_name.setText(member.getName());
        }
        if (name != null) {
            tv_member_name.setText(name);
        }

        Log.d("-진우-", "WithdrawActivity.onResume() 끝");
    }


    //패스워드 입력 체크
    private boolean checkPW(String pw, String pw1) {
        Log.d("-진우-", "pw :" + pw + ", pw1 : " + pw1);
        if (pw.length() <= 0 || pw1.length() <= 0) {
            Toast.makeText(getApplicationContext(), "패스워드를 확인해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!pw.equals(pw1)) {
            Toast.makeText(getApplicationContext(), "패스워드를 확인해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    //탈퇴페이지 초기 데이터조회(슬라이드 내 아이 목록, 계정이름, 계정이미지)
    private class WithdrawTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(WithdrawActivity.this);
        private List<Users> usersTask;
        private CircularImageView img_profileTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            Bitmap mBitmap = null;
            img_profileTask = (CircularImageView) params[0];

            //슬라이드메뉴에 있는 내 아이 목록
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Users");
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
                    com.squareup.okhttp.Response response = client.newCall(request).execute();
                    in = response.body().byteStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
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
            usersListSlideAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }
}