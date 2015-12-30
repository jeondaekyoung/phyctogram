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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Diary;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.CalendarMonthAdapter;
import knowledge_seek.com.phyctogram.listAdapter.MonthItem;
import knowledge_seek.com.phyctogram.retrofitapi.DiaryAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;

/**
 * Created by sjw on 2015-12-28.
 */
public class UsersDiaryActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;

    //레이아웃 정의
    private TextView tv_users_name;     //아이 이름 출력
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    private GridView gv_monthView;
    private CalendarMonthAdapter calendarMonthAdapter;
    private TextView tv_monthText;          //년월 출력
    private Button btn_monthPrevious;       //이전달가기
    private Button btn_monthNext;           //다음달가기
    private ImageButton imBtn_diary_write;  //일기쓰기


    //데이터 정의
    int curYear, curMonth;
    String curYearStr, curMonthStr;
    int curPosition;
    List<Diary> diaryList = new ArrayList<Diary>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "UsersDiaryActivity.onCreate() 실행");

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_calendar, ic_screen, true);
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

                //달력페이지에 출력할 아이에 관한 데이터(일기)를 가져와야한다.
                //calendarMonthAdapter.setPreviousMonth();
                //setMonthText();

                //일기 불러오기
                ReUserDiaryTask task = new ReUserDiaryTask();
                task.execute();

            }
        });

        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "슬라이드 클릭", Toast.LENGTH_SHORT).show();
                menuLeftSlideAnimationToggle();
            }
        });

        //그리드뷰 객체 참조 및 설정
        gv_monthView = (GridView)findViewById(R.id.gv_monthView);
        calendarMonthAdapter = new CalendarMonthAdapter(this);
        gv_monthView.setAdapter(calendarMonthAdapter);
        //그리드뷰(달력) 리스너 설정
        gv_monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem curItem = (MonthItem) calendarMonthAdapter.getItem(position);
                int day = curItem.getDay();

                //Toast.makeText(getApplicationContext(), day + " 일이 선택되었습니다", Toast.LENGTH_LONG).show();

                //선택한 날짜 저장하여 배경색을 준다
                //curPosition = position;
                //calendarMonthAdapter.setSelectedPosition(position);

                /*//저장된 일정 불러오기
                String outSchedule = calendarMonthAdapter.getDiary(position);
                if (outSchedule != null) {
                    scheduleInput.setText(outSchedule);
                } else {
                    scheduleInput.setText("");
                }*/

                //calendarMonthAdapter.notifyDataSetChanged();
            }
        });

        //년월출력
        tv_monthText = (TextView)findViewById(R.id.tv_monthText);
        setMonthText();

        //이전달가기, 다음달가기
        btn_monthPrevious = (Button)findViewById(R.id.btn_monthPrevious);
        btn_monthPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarMonthAdapter.setPreviousMonth();
                setMonthText();

                //일기 불러오기
                ReUserDiaryTask task = new ReUserDiaryTask();
                task.execute();

                //calendarMonthAdapter.notifyDataSetChanged();

            }
        });
        btn_monthNext = (Button)findViewById(R.id.btn_monthNext);
        btn_monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarMonthAdapter.setNextMonth();
                setMonthText();

                //일기 불러오기
                ReUserDiaryTask task = new ReUserDiaryTask();
                task.execute();

                //calendarMonthAdapter.notifyDataSetChanged();

            }
        });

        //일기쓰기
        imBtn_diary_write = (ImageButton)findViewById(R.id.imBtn_diary_write);
        imBtn_diary_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "일기 쓰기 클릭", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DiaryWriteActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
            }
        });


        curPosition = -1;

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "UsersDiaryActivity.onResume() 실행");

        //슬라이드메뉴 셋팅(내 아이 목록, 계정이름, 계정이미지, 일기 목록)
        UsersDiaryTask task = new UsersDiaryTask();
        task.execute(img_profile);

        Log.d("-진우-", "UsersDiaryActivity.onResume() : " + member.toString());

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

        Log.d("-진우-", "UsersDiaryActivity.onResume() 끝");
    }


    //년월 출력
    private void setMonthText(){
        curYear = calendarMonthAdapter.getCurYear();
        curMonth = calendarMonthAdapter.getCurMonth();
        tv_monthText.setText(curYear + "년 " + (curMonth+1) + "월");

        curYearStr = String.valueOf(curYear);
        curMonthStr = String.valueOf(curMonth+1);
        if(curMonthStr.length() == 1){
            curMonthStr = "0".concat(curMonthStr);
        }

    }

    //달력페이지 초기 데이터조회(슬라이드 내 아이 목록, 내 이미지, 일기 목록)
    private class UsersDiaryTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(UsersDiaryActivity.this);
        private List<Users> usersTask;
        private CircularImageView img_profileTask;
        private List<Diary> diarysTask;

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
            img_profileTask = (CircularImageView)params[0];

            //슬라이드메뉴에 있는 내 아이 목록
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Users");
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            //일기 목록 읽어오기
            DiaryAPI service1 = ServiceGenerator.createService(DiaryAPI.class, "Diary");
            Call<List<Diary>> call1 = service1.findDiaryByUserSeqYearMt(nowUsers.getUser_seq(), curYearStr, curMonthStr);
            try {
                diarysTask = call1.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "일기 목록 읽어오기 실패");
            }

            //이미지 불러오기
            String image_url = null;
            if(member.getJoin_route().equals("kakao")){
                image_url = member.getKakao_thumbnailimagepath();
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


            //일기
            if(diarysTask != null && diarysTask.size() > 0){
                diaryList = diarysTask;
                Log.d("-진우-", "일기 갯수 : " + diaryList.size());
                for(Diary d : diaryList) {
                    Log.d("-진우-", "일기 : " + d.toString());
                }
            } else {
                diaryList.clear();
            }

            //일기를 달력에 뿌려주기
            calendarMonthAdapter.setDiaryList(diaryList);
            calendarMonthAdapter.notifyDataSetChanged();


            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }

    //일기 목록 다시 읽어오기
    private class ReUserDiaryTask extends AsyncTask<Void, Void, Void>{

        private ProgressDialog dialog = new ProgressDialog(UsersDiaryActivity.this);
        private List<Diary> diarysTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //일기 목록 읽어오기
            DiaryAPI service = ServiceGenerator.createService(DiaryAPI.class, "Diary");
            Call<List<Diary>> call = service.findDiaryByUserSeqYearMt(nowUsers.getUser_seq(), curYearStr, curMonthStr);
            try {
                diarysTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "일기 목록 읽어오기 실패");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //일기
            diaryList.clear();
            if(diarysTask != null && diarysTask.size() > 0){
                diaryList.addAll(diarysTask);
                Log.d("-진우-", "일기 갯수 : " + diaryList.size());
                for(Diary d : diaryList){
                    Log.d("-진우-", "일기 : " + d.toString());
                }
            }
            //일기를 달력에 뿌려주기
            calendarMonthAdapter.setDiaryList(diaryList);
            calendarMonthAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }
}
