package knowledge_seek.com.phyctogram;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Diary;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.DiaryAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;

/**
 * Created by dkfka on 2015-12-08.
 */
public class DiaryWriteActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //레이아웃 정의
    private TextView tv_users_name;     //아이 이름 출력

    private EditText et_diary_date;     //날짜
    private Button btn_diary_save;      //일기 저장
    private EditText et_title;
    private EditText et_contents;

    //데이터 정의
    int year, month, day;
    Diary diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "DiaryWriteActivity.onCreate() 실행");

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_diary_write, ic_screen, true);
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
        et_diary_date = (EditText)findViewById(R.id.et_diary_date);

        //달력 대화상자 띄우기
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        et_diary_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DiaryWriteActivity.this, dateSetListener, year, month, day).show();
                setTheme(R.style.AppTheme);
            }
        });

        et_title = (EditText)findViewById(R.id.et_title);
        et_contents = (EditText)findViewById(R.id.et_contents);
        //일기 글 저장
        btn_diary_save = (Button)findViewById(R.id.btn_diary_save);
        btn_diary_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("-진우-", "저장하기");

                if(nowUsers == null){
                    Toast.makeText(getApplicationContext(), "내 아이 관리에서 아이를 등록해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                diary = new Diary();
                diary.setTitle(et_title.getText().toString());
                diary.setContents(et_contents.getText().toString());
                diary.setUser_seq(nowUsers.getUser_seq());


                //Log.d("-진우-", "날짜 : " + et_diary_date.getText().toString().length());
                if(et_diary_date.getText().toString().length() <= 0){
                    Toast.makeText(getApplicationContext(), "날짜를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //Log.d("-진우-", "날짜 : " + et_diary_date.getText().toString());
                    String[] date = et_diary_date.getText().toString().split("-");
                    //Log.d("-진우-", String.valueOf(date.length));
                    diary.setWritng_year(date[0]);
                    diary.setWritng_mt(date[1]);
                    diary.setWritng_de(date[2]);
                }

                if(!checkDiary(diary)){
                    return;
                }

                Log.d("-진우-", "저장 : " + Utility.diary2json(diary));
                RegisterDiaryTask task = new RegisterDiaryTask(diary);
                task.execute();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "DiaryWriteActivity.onResume() 실행");

        //슬라이드메뉴 셋팅(내 아이 목록, 계정이름, 계정이미지)
        DiaryWriteTask task = new DiaryWriteTask();
        task.execute(img_profile);

        Log.d("-진우-", "DiaryWriteActivity.onResume() : " + member.toString());

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

        Log.d("-진우-", "DiaryWriteActivity.onResume() 끝");
    }

    //날짜 입력
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.valueOf(year).concat("-").concat(dateFormat(monthOfYear + 1)).concat("-").concat(dateFormat(dayOfMonth));
            et_diary_date.setText(msg);
        }
    };
    //날짜가 한자리일때 앞에 0을 붙이자.
    private String dateFormat(int x){
        String s = String.valueOf(x);
        if(s.length() == 1){
            s = "0".concat(s);
        }
        return s;
    }

    //일기쓰기 페이지 초기 데이터조회(슬라이드 내 아이 목록, 계정이름, 계정이미지)
    private class DiaryWriteTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(DiaryWriteActivity.this);
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
            img_profileTask = (CircularImageView)params[0];

            //슬라이드메뉴에 있는 내 아이 목록
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Users");
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            String image_url = null;
            if(member.getJoin_route().equals("kakao")){
                image_url = member.getKakao_thumbnailimagepath();
                //이미지 불러오기
                InputStream in = null;
                try {
                    Log.d("-진우-", "이미지 주소 : " + image_url);
                    in = new URL(image_url).openStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else if(member.getJoin_route().equals("facebook")){
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
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                Log.d("-진우-", "이미지읽어옴");
                img_profileTask.setImageBitmap(bitmap);
            }

            if(usersTask != null && usersTask.size() > 0){
                Log.d("-진우-", "내 아이는 몇명? " + usersTask.size());
                for(Users u : usersTask){
                    Log.d("-진우-", "내 아이 : " + u.toString());
                }
                usersList = usersTask;

                usersListSlideAdapter.setUsersList(usersList);
                if(nowUsers == null){
                    nowUsers = usersTask.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());
                tv_users_name.setText(nowUsers.getName());
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

    //Diary의 내용체크
    private boolean checkDiary(Diary diary){
        if(diary.getTitle().length() <= 0 || diary.getContents().length() <= 0){
            Toast.makeText(getApplicationContext(), "제목과 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //일기 저장
    private class RegisterDiaryTask extends AsyncTask<Void, Void, String>{

        private Diary diaryTask;
        private ProgressDialog dialog = new ProgressDialog(DiaryWriteActivity.this);

        public RegisterDiaryTask(Diary diary) {
            this.diaryTask = diary;
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
            String result = null;
            DiaryAPI service = ServiceGenerator.createService(DiaryAPI.class);
            Call<String> call = service.registerDiary(diaryTask);
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
                Toast.makeText(getApplicationContext(), "저장하였습니다", Toast.LENGTH_LONG).show();
            } else if(result != null && result.equals("exist")){
                Toast.makeText(getApplicationContext(), "이미 일기를 작성하였습니다", Toast.LENGTH_LONG).show();
            } else {
                Log.d("-진우-", "일기 저장에 실패하였습니다");
            }

            dialog.dismiss();
            super.onPostExecute(result);
            finish();
        }
    }
}
