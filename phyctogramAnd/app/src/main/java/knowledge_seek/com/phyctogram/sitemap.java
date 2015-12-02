package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dkfka on 2015-11-25.
 */
public class sitemap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitemap);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_useradd:
                Intent btn1 = new Intent(this, UseraddActivity.class);
                startActivity(btn1);
                break;

            case R.id.btn_usermod:
                Intent btn2 = new Intent(this, UsermodActivity.class);
                startActivity(btn2);
                break;

            case R.id.btn_pwmod:
                Intent btn3 = new Intent(this, PwmodActivity.class);
                startActivity(btn3);
                break;

            case R.id.btn_record:
                Intent btn4 = new Intent(this, RecordActivity.class);
                startActivity(btn4);
                break;

            case R.id.btn_main:
                Intent btn6 = new Intent(this, MainActivity.class);
                startActivity(btn6);
                break;

            case R.id.btn_join:
                Intent btn7 = new Intent(this, JoinActivity.class);
                startActivity(btn7);
                break;

            case R.id.btn_write:
                Intent btn8 = new Intent(this, WriteActivity.class);
                startActivity(btn8);
                break;
        }
    }
}