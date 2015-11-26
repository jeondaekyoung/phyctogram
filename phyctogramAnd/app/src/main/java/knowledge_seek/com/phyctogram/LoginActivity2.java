package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dkfka on 2015-11-24.
 */
public class LoginActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.member_login:
                /*Intent login = new Intent(this, MainActivity.class);
                startActivity(login);*/
                break;
            case R.id.sitemap:
                /*Intent sm = new Intent(this, sitemap.class);
                startActivity(sm);*/
                break;
        }
    }
}