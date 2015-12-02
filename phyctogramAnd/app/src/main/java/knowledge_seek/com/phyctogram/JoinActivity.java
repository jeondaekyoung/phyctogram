package knowledge_seek.com.phyctogram;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

/**
 * Created by dkfka on 2015-11-26.
 */
public class JoinActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox alert, alert2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        alert = (CheckBox) findViewById(R.id.agreement);
        alert2 = (CheckBox) findViewById(R.id.agreement2);
        alert.setOnClickListener(this);
        alert2.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.agreement:
                if (v == alert) {
                    Context mContext = getApplicationContext();
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                    View layout;
                    layout = inflater.inflate(R.layout.popup_agree, (ViewGroup) findViewById(R.id.agreeText));
                    AlertDialog.Builder aDialog = new AlertDialog.Builder(JoinActivity.this);

                    aDialog.setTitle("이용약관");
                    aDialog.setView(layout);

                    aDialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog ad = aDialog.create();
                    ad.show();
                    break;
                }

            case R.id.agreement2:
                if (v == alert2) {
                    Context mContext2 = getApplicationContext();
                    LayoutInflater inflater2 = (LayoutInflater) mContext2.getSystemService(LAYOUT_INFLATER_SERVICE);

                    View layout;
                    layout = inflater2.inflate(R.layout.popup_agree2, (ViewGroup) findViewById(R.id.agreeText));
                    AlertDialog.Builder aDialog2 = new AlertDialog.Builder(JoinActivity.this);

                    aDialog2.setTitle("개인정보취급방침");
                    aDialog2.setView(layout);

                    aDialog2.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog ad2 = aDialog2.create();
                    ad2.show();
                    break;
                }
        }
    }
}
