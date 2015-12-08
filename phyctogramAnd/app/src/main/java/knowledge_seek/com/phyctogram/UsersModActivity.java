package knowledge_seek.com.phyctogram;

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
public class UsersModActivity extends BaseActivity {
    public static final String HTTPADDR = "http://117.52.89.181";

    /* slide menu */
    public static Button bt_left;

    //데이터
    private Users users = null;

    //레이아웃 정의
    private EditText et_name;
    private EditText et_initials;
    private DatePicker dp_lifedate;
    private RadioGroup rg_sexdstn;
    private RadioButton rb_female;
    private RadioButton rb_male;
    private Button btn_usersmod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_mod);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            users = (Users) bundle.getSerializable("users");
            Log.d("-진우-", "UsersModActivity 에서 " + users.toString());
        }

        //사이드 메뉴
        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });
        initSildeMenu();


        //레이아웃 정의
        et_name = (EditText)findViewById(R.id.et_name);
        et_name.setText(users.getName());
        et_initials = (EditText)findViewById(R.id.et_initials);
        et_initials.setText(users.getInitials());
        dp_lifedate = (DatePicker)findViewById(R.id.dp_lifedate);
        dp_lifedate.updateDate(Integer.valueOf(users.getLifyea()), Integer.valueOf(users.getMt())-1, Integer.valueOf(users.getDe()));
        rg_sexdstn = (RadioGroup)findViewById(R.id.rg_sexdstn);
        rb_female = (RadioButton)findViewById(R.id.rb_female);
        rb_male = (RadioButton)findViewById(R.id.rb_male);
        if(users.getSexdstn().equals("male")){
            rb_male.setChecked(true);
            rb_female.setChecked(false);
        } else {
            rb_female.setChecked(true);
            rb_male.setChecked(false);
        }
        btn_usersmod = (Button)findViewById(R.id.btn_usersmod);
        btn_usersmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }



}
