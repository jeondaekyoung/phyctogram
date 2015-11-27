package knowledge_seek.com.phyctogram.kakao.common;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import knowledge_seek.com.phyctogram.LoginActivity;
import knowledge_seek.com.phyctogram.kakao.common.widget.WaitingDialog;

/**
 * Created by sjw on 2015-11-26.
 */
public class BaseActivity extends Activity {
    protected static Activity self;

    @Override
    protected void onResume() {
        super.onResume();
        GlobalApplication.setCurrentActivity(this);
        self = BaseActivity.this;
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences() {
        Activity currActivity = GlobalApplication.getCurrentActivity();
        if(currActivity != null && currActivity.equals(this)){
            GlobalApplication.setCurrentActivity(null);
        }
    }

    protected static void showWaitingDialog(){
        WaitingDialog.showWaitingDialog(self);
    }

    protected static void cancelWaitingDialog(){
        WaitingDialog.cancelWaitingDialog();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectSignupActivity() {
        Log.d("-진우", "SampleSignupActivity 불러온다.");
        final Intent intent = new Intent(this, SampleSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}
