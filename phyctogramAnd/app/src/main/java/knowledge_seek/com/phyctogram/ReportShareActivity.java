package knowledge_seek.com.phyctogram;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by dkfka on 2015-12-11.
 */
public class ReportShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_share);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                finish();
                break;
        }
    }
}
