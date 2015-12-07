package knowledge_seek.com.phyctogram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-27.
 */
public class UsersDropActivity extends BaseActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_drop);


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

            case R.id.btn_dropout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("회원 탈퇴시 모든 데이터가 삭제되므로 복구가 되지 않습니다. 탈퇴 하시겠습니까?")
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
        }

    }
}