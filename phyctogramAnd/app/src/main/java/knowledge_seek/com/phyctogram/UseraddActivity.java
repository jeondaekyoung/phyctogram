package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-25.
 */
public class UseraddActivity extends BaseActivity {

    //사이드매뉴
    public static Button bt_left;
    public static Button btn1;
    public static Button btn2;
    public static Button btn3;
    public static Button btn4;

    //데이터
    private Users users;

    //레이아웃 정의
    private EditText et_name;
    private EditText et_initials;
    private DatePicker dp_lifedate;
    private RadioGroup rg_sexdstn;
    private RadioButton rb_female;
    private RadioButton rb_male;
    private Button btn_useradd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useradd);

        //사이드매뉴
        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        /*btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);*/

        //슬라이딩, 메뉴 BaseActivity.class
        initSildeMenu();

        users = new Users();

        //레이아웃 정의
        et_name = (EditText)findViewById(R.id.et_name);
        et_initials = (EditText)findViewById(R.id.et_initials);
        dp_lifedate = (DatePicker)findViewById(R.id.dp_lifedate);
        rg_sexdstn = (RadioGroup)findViewById(R.id.rg_sexdstn);
        rb_female = (RadioButton)findViewById(R.id.rb_female);
        rb_male = (RadioButton)findViewById(R.id.rb_male);
        btn_useradd = (Button)findViewById(R.id.btn_useradd);
        btn_useradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.d("-진우-", dp_lifedate.getYear() + "/" + (dp_lifedate.getMonth()+1) + "/" + dp_lifedate.getDayOfMonth());
                Log.d("-진우-", "#" + et_name.getText().toString() + "#" + " #" + et_initials.getText().toString() + "#");
                Log.d("-진우-", String.valueOf(rb_female.isChecked()));
                Log.d("-진우-", String.valueOf(rb_male.isChecked()));*/

                //이름 및 이니셜 체크
                users.setName(et_name.getText().toString());
                users.setInitials(et_initials.getText().toString());
                users.setLifyea(String.valueOf(dp_lifedate.getYear()));
                users.setMt(String.valueOf((dp_lifedate.getMonth() + 1)));
                users.setDe(String.valueOf(dp_lifedate.getDayOfMonth()));
                if(rb_female.isChecked()){
                    users.setSexdstn("female");
                } else if(rb_male.isChecked()){
                    users.setSexdstn("male");
                }

                if(!checkUsers(users)){
                    return ;
                }

            }
        });

    }

    //users의 내용 체크
    private boolean checkUsers(Users users){
        Log.d("-진우-", users.toString());
        if(users.getName().length() <= 0 || users.getInitials().length() <= 0){
            return false;
        }
        return true;
    }

}