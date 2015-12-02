package knowledge_seek.com.phyctogram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-12-02.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

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
    TextView list_notice, list_equip, list_logout, list_pwmod, list_userdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


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

        list_notice = (TextView) findViewById(R.id.list_notice);
        list_equip = (TextView) findViewById(R.id.list_equip);
        list_logout = (TextView) findViewById(R.id.list_logout);
        list_pwmod = (TextView) findViewById(R.id.list_pwmod);
        list_userdrop = (TextView) findViewById(R.id.list_userdrop);
        list_notice.setOnClickListener(this);
        list_equip.setOnClickListener(this);
        list_logout.setOnClickListener(this);
        list_pwmod.setOnClickListener(this);
        list_userdrop.setOnClickListener(this);

        initSildeMenu();

    }

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

            case R.id.list_notice:

                break;
            case R.id.list_equip:
                Intent equip = new Intent(this, EquipmentActivity.class);
                startActivity(equip);
                break;
            case R.id.list_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("로그아웃 하시겠습니까?")
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                finish();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case R.id.list_pwmod:
                Intent pwmod = new Intent(this, PwmodActivity.class);
                startActivity(pwmod);
                break;
            case R.id.list_userdrop:
                Intent userdrop = new Intent(this, UserDropActivity.class);
                startActivity(userdrop);
                break;
        }

    }
}