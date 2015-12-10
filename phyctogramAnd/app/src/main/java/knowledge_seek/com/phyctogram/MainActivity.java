package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-25.
 */
public class MainActivity extends BaseActivity {
    public static final String HTTPADDR = "http://117.52.89.181";

    //레이아웃정의
    private LinearLayout ic_screen;
    private Button btn_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_main, ic_screen, true);

        initSildeMenu();

        //레이아웃 정의
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}