package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-25.
 */
public class UseraddActivity  extends BaseActivity  {

    public static Button bt_left;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useradd);

        bt_left = (Button) findViewById(R.id.bt_left);
        //bt_left.setOnClickListener(this);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_LONG).show();
            }
        });

    }
}
