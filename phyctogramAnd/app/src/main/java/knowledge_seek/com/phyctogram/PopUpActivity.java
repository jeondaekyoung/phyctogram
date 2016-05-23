package knowledge_seek.com.phyctogram;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import knowledge_seek.com.phyctogram.util.EqAsyncTask;


public class PopUpActivity extends Activity implements View.OnClickListener{

	private String ssid;
	private String capabilities;
	private String ipAddress;

	private TextView tv_ssid;
	private EditText edit_password;

	private Button btn_close_popup;
	private Button btn_pwdOk;

	public static EquipmentActivity activity;
	public static WifiPopUpActivity wifiPopUpActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.popup_wifi);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		btn_close_popup = (Button) findViewById(R.id.btn_close_popup);
		btn_pwdOk = (Button) findViewById(R.id.btn_pwdOk);

		btn_close_popup.setOnClickListener(this);
		btn_pwdOk.setOnClickListener(this);

		ssid = getIntent().getStringExtra("ssid");
		capabilities = getIntent().getStringExtra("capabilities");
		ipAddress = getIntent().getStringExtra("ipAddress");


		tv_ssid = (TextView) findViewById(R.id.tv_ssid);
		edit_password = (EditText) findViewById(R.id.edit_password);
		tv_ssid.setText(ssid);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_pwdOk){
			/*Intent i = new Intent(PopUpActivity.this,LoginActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);*/
			Log.d("-진우-", "PopUpActivity ssid : " + ssid + ", capabilities : " + capabilities + ", edit_password : " + edit_password.getText());

			if (activity !=null) {
				activity.connectWifi(ssid, edit_password.getText().toString(), capabilities);
			}else{
				String password = edit_password.getText().toString();

				SendMessageThread sendMessageThread = new SendMessageThread(true, 0, ipAddress, ssid, password);
				sendMessageThread.start();
			}
			finish();

		}else if(v.getId() == R.id.btn_close_popup){
			finish();
		}
	}

	class SendMessageThread extends Thread {
		private ProgressDialog dialog = new ProgressDialog(wifiPopUpActivity);
		private boolean isPlay = false;
		private int i;
		private String ipAddress;
		private String ssid;
		private String pw;

		public SendMessageThread(boolean isPlay, int i, String ipAddress, String ssid, String pw) {
			this.i = i;
			this.isPlay = isPlay;
			this.ipAddress = ipAddress;
			this.ssid = ssid;
			this.pw = pw;
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setMessage(getString(R.string.commonActivity_wait));
			dialog.show();
		}

		public void stopThread() {
			dialog.dismiss();
			isPlay = !isPlay;
		}

		@Override
		public void run() {
			super.run();
			while (isPlay) {
				if(i==0){
					Log.d("-진우-","1");
					new EqAsyncTask().execute("192.168.4.1:80", "SSID", ssid+"??");
				}else if(i==1){
					Log.d("-진우-","2");
					new EqAsyncTask().execute("192.168.4.1:80", "PW", pw+"??");
				}else if(i==2){
					Log.d("-진우-","3");
					new EqAsyncTask().execute("192.168.4.1:80", "END_SERVER","0");
				}else{
					Log.d("-진우-","4");
					wifiPopUpActivity.connectWifi(ssid, pw, capabilities);
				}
				if (i==3){
					stopThread();
				}else {
					i++;
				}
				try {
					Thread.sleep(1000*5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
