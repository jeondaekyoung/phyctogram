package knowledge_seek.com.phyctogram;

import android.app.Activity;
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
                Intent btn1 = new Intent(this, UsersAddActivity.class);
                startActivity(btn1);
                break;

            case R.id.btn_usermod:
                Intent btn2 = new Intent(this, UsersModActivity.class);
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
                Intent btn8 = new Intent(this, CommunityWriteActivity.class);
                startActivity(btn8);
                break;

            case R.id.btn_userdrop:
                Intent btn9 = new Intent(this, UsersDropActivity.class);
                startActivity(btn9);
                break;

            case R.id.btn_pwfind:
                Intent btn10 = new Intent(this, PwfindActivity.class);
                startActivity(btn10);
                break;

            case R.id.btn_equip:
                Intent btn11 = new Intent(this, EquipmentActivity.class);
                startActivity(btn11);
                break;

            case R.id.btn_calendar:
                Intent btn12 = new Intent(this, CalendarActivity.class);
                startActivity(btn12);
                break;
            case R.id.btn_set:
                Intent btn13 = new Intent(this, SettingActivity.class);
                startActivity(btn13);
                break;
            case R.id.btn_login2:
                Intent btn14 = new Intent(this, LoginActivity2.class);
                startActivity(btn14);
                break;
            case R.id.btn_boardlist:
                Intent btn15 = new Intent(this, CommunityListActivity.class);
                startActivity(btn15);
                break;
            case R.id.btn_usermanage:
                Intent btn16 = new Intent(this, UsersManageActivity.class);
                startActivity(btn16);
                break;
            case R.id.btn_boardview:
                Intent btn17 = new Intent(this, CommunityViewActivity.class);
                startActivity(btn17);
                break;
            case R.id.btn_report:
                Intent btn18 = new Intent(this, CombinedChartActivity.class);
                startActivity(btn18);
                break;
            case R.id.btn_character:
                Intent btn19 = new Intent(this, CharacterActivity.class);
                startActivity(btn19);
                break;
            case R.id.btn_userdatainput:
                Intent btn20 = new Intent(this, Activity.class);
                startActivity(btn20);
                break;
        }
    }
}