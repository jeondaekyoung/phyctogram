package knowledge_seek.com.phyctogram;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import knowledge_seek.com.phyctogram.domain.Height;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.HeightListRecordAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.HeightAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.TimestampDes;
import retrofit.Call;

/**
 * Created by dkfka on 2015-11-25.
 */

public class RecordActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private Button btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의
    private EditText et_datepickFrom;
    private EditText et_datepickTo;
    private Button btn_findHeight;
    private ListView lv_record;
    private HeightListRecordAdapter heightListRecordAdapter;


    //데이터정의
    int year, month, day, hour, minute;
    private Height findHeight = new Height();
    private List<Height> heightList = new ArrayList<Height>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_record);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_record, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //레이아웃 정의
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "슬라이드 클릭", Toast.LENGTH_SHORT).show();
                menuLeftSlideAnimationToggle();
            }
        });
        et_datepickFrom = (EditText)findViewById(R.id.et_datepickFrom);
        et_datepickTo = (EditText)findViewById(R.id.et_datepickTo);

        //기록 조회
        lv_record = (ListView)findViewById(R.id.lv_record);
        heightListRecordAdapter = new HeightListRecordAdapter(this, heightList, R.layout.list_record);
        lv_record.setAdapter(heightListRecordAdapter);

        /*ArrayList<Listviewitem_rec> data = new ArrayList<>();
        Listviewitem_rec item = new Listviewitem_rec("15-12-04", "155cm", "1cm");
        data.add(item);
        Listviewitem_rec item2 = new Listviewitem_rec("15-12-04", "155cm", "1cm");
        data.add(item2);

        ListviewAdapter_rec adapter2 = new ListviewAdapter_rec(this, R.layout.list_record, data);
        listview.setAdapter(adapter2);*/
        //-->


        btn_findHeight = (Button)findViewById(R.id.btn_findHeight);
        btn_findHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateFrom = et_datepickFrom.getText().toString();
                String dateTo = et_datepickTo.getText().toString();
                String user_seq = String.valueOf(nowUsers.getUser_seq());
                Log.d("-진우-", "검색 : " + dateFrom + " ~ " + dateTo + ", " + user_seq);
                FindHeightByUserSeqFTTask task = new FindHeightByUserSeqFTTask(user_seq, dateFrom, dateTo);
                task.execute();
                heightListRecordAdapter.notifyDataSetChanged();
            }
        });

        //달력 대화상자 띄우기
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        et_datepickFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RecordActivity.this, dateSetListenerFrom, year, month, day).show();
                setTheme(R.style.AppTheme);
            }
        });
        et_datepickTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RecordActivity.this, dateSetListenerTo, year, month, day).show();
                setTheme(R.style.AppTheme);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //요건되는데, BaseActivity.onResume()에 있으면 안되네..
        //login, join등의 member이 없는 activity가 있기 때문에 안된다.
        //슬라이드메뉴에 있는 내 아이 목록
        updateScreenSlide();

        Log.d("-진우-", "RecordActivity 에 onResume() : " + member.toString());
        Log.d("-진우-", "RecordActivity 에 onResume() : " + nowUsers.toString());
    }

    //날짜 입력
    private DatePickerDialog.OnDateSetListener dateSetListenerFrom = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.valueOf(year).concat("-").concat(dateFormat(monthOfYear + 1)).concat("-").concat(dateFormat(dayOfMonth));
            //String msg = String.format("%d-%d-%d", year, dateFormat(monthOfYear + 1), dateFormat(dayOfMonth));
            et_datepickFrom.setText(msg);
        }
    };
    private DatePickerDialog.OnDateSetListener dateSetListenerTo = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.valueOf(year).concat("-").concat(dateFormat(monthOfYear+1)).concat("-").concat(dateFormat(dayOfMonth));
            //String msg = String.format("%d-%d-%d", year, dateFormat(monthOfYear + 1), dateFormat(dayOfMonth));
            et_datepickTo.setText(msg);
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

    private class FindHeightByUserSeqFTTask extends AsyncTask<Void, Void, List<Height>>{

        private String user_seq;
        private String dateFrom;
        private String dateTo;
        private List<Height> heightTask;

        public FindHeightByUserSeqFTTask(String user_seq, String dateFrom, String dateTo) {
            this.user_seq = user_seq;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
        }

        @Override
        protected List<Height> doInBackground(Void... params) {
            Log.d("-진우-", user_seq + ", " + dateFrom + ", " + dateTo);
            HeightAPI service = ServiceGenerator.createService(HeightAPI.class, true);
            Call<List<Height>> call = service.findHeightByUserSeqFT(user_seq, dateFrom, dateTo);
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
                for(Height h : heights){
                    Log.d("-진우-", "기록 : " + h.toString());
                }
                heightListRecordAdapter.setHeights(heights);
                heightList = heights;
            } else {
                Log.d("-진우-", "성공했으나 기록이 없습니다.");
            }
        }
    }
}
