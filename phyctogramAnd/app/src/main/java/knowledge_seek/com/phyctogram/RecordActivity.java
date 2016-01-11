package knowledge_seek.com.phyctogram;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Height;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.HeightListRecordAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.HeightAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dkfka on 2015-11-25.
 */

public class RecordActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //레이아웃정의
    private TextView tv_datepickFrom;
    private TextView tv_datepickTo;
    private Button btn_findHeight;
    private ListView lv_record;
    private HeightListRecordAdapter heightListRecordAdapter;
    private TextView tv_users_name;     //아이 이름 출력


    //데이터정의
    int year, month, day;
    private List<Height> heightList = new ArrayList<Height>();
    //리스트뷰 더보기기능 데이터정의
    private boolean lastListViewVisible = false;        //화면에 리스트의 마지막 아이템이 보여지는지 체크
    private int pageCnt = 0;                //리스트뷰의 목록 페이지 번호


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_record, ic_screen, true);
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

                tv_users_name.setText(nowUsers.getName());
                heightList.clear();
                heightListRecordAdapter.setHeights(heightList);
                heightListRecordAdapter.notifyDataSetChanged();
            }
        });

        //레이아웃 정의
        btn_left = (ImageButton)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "슬라이드 클릭", Toast.LENGTH_SHORT).show();
                menuLeftSlideAnimationToggle();
            }
        });
        tv_datepickFrom = (TextView)findViewById(R.id.tv_datepickFrom);
        tv_datepickTo = (TextView)findViewById(R.id.tv_datepickTo);

        //기록 조회 리스트뷰 셋팅
        lv_record = (ListView)findViewById(R.id.lv_record);
        heightListRecordAdapter = new HeightListRecordAdapter(this, heightList, R.layout.list_record);
        lv_record.setAdapter(heightListRecordAdapter);
        lv_record.setOnScrollListener(scrollListener);

        //리스트뷰 롱클릭 -> 내 아이 삭제됨
        lv_record.setLongClickable(true);
        lv_record.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Height height = (Height)heightListRecordAdapter.getItem(position);
                Log.d("-진우-", "삭제 : " + height.toString());
                AlertDialog.Builder dialog = new AlertDialog.Builder(RecordActivity.this);
                dialog.setTitle("삭제");
                dialog.setMessage("다음 데이터를 삭제하시겠습니까? " + height.getMesure_date() + ", " + height.getHeight() + "cm");
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        HeightAPI service = ServiceGenerator.createService(HeightAPI.class);
                        Call<String> call = service.delHeightByHeightSeq(height.getHeight_seq());
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                Log.d("-진우-", "키 삭제 성공 결과 : " + response.body());
                                Toast.makeText(getApplicationContext(), "키 삭제에 성공하였습니다", Toast.LENGTH_LONG).show();
                                //Log.d("-진우-", "삭제전 : " + heightList.size());
                                heightList.remove(height);
                                //Log.d("-진우-", "삭제후 : " + heightList.size());
                                for(int i =0; i < heightList.size()-1 ; i++){
                                    heightList.get(i).setGrow(String.format("%.1f", (heightList.get(i).getHeight() - heightList.get(i+1).getHeight()) ));
                                }
                                heightList.get(heightList.size()- 1).setGrow("0");
                                heightListRecordAdapter.setHeights(heightList);
                                heightListRecordAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onFailure(Throwable t) {

                            }
                        });

                    }

                });
                dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });


        //기록 조회 검색 클
        btn_findHeight = (Button)findViewById(R.id.btn_findHeight);
        btn_findHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateFrom = tv_datepickFrom.getText().toString();
                String dateTo = tv_datepickTo.getText().toString();

                if(!checkDate(dateFrom, dateTo)){
                    return;
                }
                if(nowUsers == null){
                    Toast.makeText(getApplicationContext(), "내 아이 관리에서 아이를 등록해주세요", Toast.LENGTH_SHORT).show();
                    return ;
                }

                String user_seq = String.valueOf(nowUsers.getUser_seq());
                //Log.d("-진우-", "검색 : " + dateFrom + " ~ " + dateTo + ", " + user_seq);
                heightList.clear();     //리스트 초기화
                pageCnt = 0;            //페이지수 초기화

                FindHeightByUserSeqFTTask task = new FindHeightByUserSeqFTTask(user_seq, dateFrom, dateTo, pageCnt);
                task.execute();
            }
        });

        //달력 대화상자 띄우기
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tv_datepickFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datepickFrom = tv_datepickFrom.getText().toString();
                if(datepickFrom == null || datepickFrom.length() <= 0) {
                    new DatePickerDialog(RecordActivity.this, dateSetListenerFrom, year, month, day).show();
                } else {
                    new DatePickerDialog(RecordActivity.this, dateSetListenerFrom, Integer.valueOf(datepickFrom.substring(0,4)),
                            Integer.valueOf(datepickFrom.substring(5,7))-1, Integer.valueOf(datepickFrom.substring(8))).show();
                }
                setTheme(R.style.AppTheme);
            }
        });
        tv_datepickTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datepickTo = tv_datepickTo.getText().toString();
                if(datepickTo == null || datepickTo.length() <= 0){
                    new DatePickerDialog(RecordActivity.this, dateSetListenerTo, year, month, day).show();
                } else {
                    new DatePickerDialog(RecordActivity.this, dateSetListenerTo, Integer.valueOf(datepickTo.substring(0,4)),
                            Integer.valueOf(datepickTo.substring(5,7))-1, Integer.valueOf(datepickTo.substring(8))).show();
                }
                setTheme(R.style.AppTheme);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //슬라이드메뉴 셋팅(내 아이목록, 계정이미지)
        RecordTask task = new RecordTask();
        task.execute(img_profile);

        Log.d("-진우-", "RecordActivity 에 onResume() : " + member.toString());

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

    //날짜 입력
    private DatePickerDialog.OnDateSetListener dateSetListenerFrom = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.valueOf(year).concat("-").concat(dateFormat(monthOfYear + 1)).concat("-").concat(dateFormat(dayOfMonth));
            //String msg = String.format("%d-%d-%d", year, dateFormat(monthOfYear + 1), dateFormat(dayOfMonth));
            tv_datepickFrom.setText(msg);
        }
    };
    private DatePickerDialog.OnDateSetListener dateSetListenerTo = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.valueOf(year).concat("-").concat(dateFormat(monthOfYear+1)).concat("-").concat(dateFormat(dayOfMonth));
            //String msg = String.format("%d-%d-%d", year, dateFormat(monthOfYear + 1), dateFormat(dayOfMonth));
            tv_datepickTo.setText(msg);
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

    //기록조회페이지 초기 데이터조회(슬라이드 내 아이 목록, 계정이미지)
    private class RecordTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(RecordActivity.this);
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
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
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
                tv_users_name.setText(nowUsers.getName());
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }

    //날짜 입력 체크
    private boolean checkDate(String dateFrom, String dateTo){
        if(dateFrom.length() <= 0 || dateTo.length() <= 0){
            Toast.makeText(getApplicationContext(), "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    //기록조회
    private class FindHeightByUserSeqFTTask extends AsyncTask<Void, Void, List<Height>>{

        private String user_seq;
        private String dateFrom;
        private String dateTo;
        private int pageCntTask;
        private List<Height> heightTask;
        private ProgressDialog dialog = new ProgressDialog(RecordActivity.this);

        public FindHeightByUserSeqFTTask(String user_seq, String dateFrom, String dateTo, int pageCntTask) {
            this.user_seq = user_seq;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.pageCntTask = pageCntTask;
        }

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.show();

            super.onPreExecute();
        }

        @Override
        protected List<Height> doInBackground(Void... params) {
            Log.d("-진우-", user_seq + ", " + dateFrom + ", " + dateTo);
            HeightAPI service = ServiceGenerator.createService(HeightAPI.class, "RecordActivity");
            Call<List<Height>> call = service.findHeightByUserSeqFT(user_seq, dateFrom, dateTo, pageCntTask);
            try {
                heightTask = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "기록조회 실패");
            }
            return heightTask;
        }

        @Override
        protected void onPostExecute(List<Height> heights) {
            if(heights != null && heights.size() > 0){
                /*for(Height h : heights){
                    Log.d("-진우-", "목록 : " + h.toString());
                }*/
                Log.d("-진우-", "읽어온 목록은 " + heights.size() + " 개 있습니다");
                heightList.addAll(heights);
                Log.d("-진우-", "총 목록은 " + heightList.size() + " 개 입니다");

                for(int i=0; i < heightList.size()-1 ; i++){
                    //Log.d("-진우-", heights.get(i).toString());
                    //Log.d("-진우-", String.valueOf(heights.get(i).getHeight()));
                    //Log.d("-진우-", String.valueOf(heights.get(i+1).getHeight()));
                    //Log.d("-진우-",  String.valueOf(heights.get(i).getHeight() - heights.get(i+1).getHeight()) );
                    //키변화 저장
                    heightList.get(i).setGrow(String.format("%.1f", (heightList.get(i).getHeight() - heightList.get(i+1).getHeight()) ));
                }
                heightList.get(heightList.size()- 1).setGrow("0");

                heightListRecordAdapter.setHeights(heightList);
                pageCnt = pageCntTask+1;
            } else {
                Log.d("-진우-", "성공했으나 목록이 없습니다.");
            }

            /*int height = getListViewHeight(lv_record);
            lv_record.getLayoutParams().height = height;*/

            heightListRecordAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(heights);
        }
    }

    //더보기 - 스크롤리스너
    private AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            //OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을 때 발생되는 스크롤상태입니다.
            //즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
            if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastListViewVisible){
                //화면에 바닦에 닿았고, 스크롤이 멈추었다.
                Log.d("-진우-", "추가데이터 불러오기");
                String dateFrom = tv_datepickFrom.getText().toString();
                String dateTo = tv_datepickTo.getText().toString();
                String user_seq = String.valueOf(nowUsers.getUser_seq());
                FindHeightByUserSeqFTTask task = new FindHeightByUserSeqFTTask(user_seq, dateFrom, dateTo, pageCnt);
                task.execute();
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가
            //리스트 전체의 갯수(totalItemCount) - 1 보다 크거나 같을 때
            lastListViewVisible = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
        }
    };

}
