package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login:
                Intent memberlogin = new Intent(this, LoginActivity2.class);
                startActivity(memberlogin);
                break;

            case R.id.btn_login_kko:

                break;

            case R.id.btn_login_fb:

                break;
        }

    }

}
