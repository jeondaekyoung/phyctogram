package knowledge_seek.com.phyctogram;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-12-07.
 */
public class UsersManageActivity extends BaseActivity {

    //데이터
    private Member member;

    /* slide menu */
    public static Button bt_left;

    //정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_manage);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            member = (Member)bundle.getSerializable("member");
            Log.d("-진우-", "MainActivity 에서 " + member.toString());
        }

        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });
        initSildeMenu();


    }


}
