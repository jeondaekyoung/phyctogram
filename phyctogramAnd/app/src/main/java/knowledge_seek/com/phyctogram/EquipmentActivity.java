package knowledge_seek.com.phyctogram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-12-02.
 */
public class EquipmentActivity extends BaseActivity implements View.OnClickListener {

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);


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

            case R.id.addEquip:

                final EditText et = new EditText(this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("기기 추가")
                        .setMessage("일련번호를 입력하세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setView(et)
                        .show();
                break;
        }

    }
}