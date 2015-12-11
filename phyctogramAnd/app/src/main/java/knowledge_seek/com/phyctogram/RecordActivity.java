package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-25.
 */

public class RecordActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private Button btn_left;
    private LinearLayout ic_screen;

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

        //커뮤니티 글 목록
        //ListView listview = (ListView) findViewById(R.id.list_record);

        /*ArrayList<Listviewitem_rec> data = new ArrayList<>();
        Listviewitem_rec item = new Listviewitem_rec("15-12-04", "155cm", "1cm");
        data.add(item);
        Listviewitem_rec item2 = new Listviewitem_rec("15-12-04", "155cm", "1cm");
        data.add(item2);

        ListviewAdapter_rec adapter2 = new ListviewAdapter_rec(this, R.layout.list_record, data);
        listview.setAdapter(adapter2);*/

        //레이아웃 정의
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        //달력 대화상자 띄우기
        /*GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        editText1 = (EditText) findViewById(R.id.datepick1);
        editText2 = (EditText) findViewById(R.id.datepick2);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RecordActivity.this, dateSetListener, year, month, day).show();
                setTheme(R.style.AppTheme);
            }
        });
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RecordActivity.this, dateSetListener2, year, month, day).show();
                setTheme(R.style.AppTheme);
            }
        });*/
    }

    //날짜 입력
    /*private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.format("%d / %d / %d", year, monthOfYear + 1, dayOfMonth);
            editText1.setText(msg);
        }
    };
    private DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.format("%d / %d / %d", year, monthOfYear + 1, dayOfMonth);
            editText2.setText(msg);
        }
    };*/

    @Override
    protected void onResume() {
        super.onResume();

        //요건되는데, BaseActivity.onResume()에 있으면 안되네..
        //login, join등의 member이 없는 activity가 있기 때문에 안된다.
        //슬라이드메뉴에 있는 내 아이 목록
        updateScreenSlide();

        Log.d("-진우-", "RecordActivity 에 onResume() : " + member.toString());
    }
}