package knowledge_seek.com.phyctogram;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-25.
 */
public class RecordActivity extends BaseActivity implements View.OnClickListener {

    /* slide menu */
    //public static DisplayMetrics metrics;
    //public static LinearLayout ll_mainLayout;
    //public static LinearLayout ll_menuLayout;
    //public static FrameLayout.LayoutParams leftMenuLayoutPrams;
    //public static int leftMenuWidth;
    //public static boolean isLeftExpanded;
    public static Button bt_left;
    public static Button btn1;
    public static Button btn2;
    public static Button btn3;
    public static Button btn4;
    int year, month, day, hour, minute;
    EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        initSildeMenu();

        //달력 대화상자 띄우기
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        editText1 = (EditText)findViewById(R.id.datepick1);
        editText2 = (EditText)findViewById(R.id.datepick2);

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
        });
    }

    //날짜 입력
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            String msg = String.format("%d / %d / %d", year, monthOfYear+1, dayOfMonth);
            editText1.setText(msg);
        }
    };
    private DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            String msg = String.format("%d / %d / %d", year, monthOfYear+1, dayOfMonth);
            editText2.setText(msg);
        }
    };


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_left:
                menuLeftSlideAnimationToggle();
                break;
            case R.id.btn1:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                break;
            case R.id.btn2:

                break;
            case R.id.btn3:

                break;
            case R.id.btn4:

                break;

        }

    }
}
