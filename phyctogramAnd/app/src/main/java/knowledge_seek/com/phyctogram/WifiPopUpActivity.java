package knowledge_seek.com.phyctogram;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Wifi;
import knowledge_seek.com.phyctogram.listAdapter.WifiListAdapter;
import knowledge_seek.com.phyctogram.util.EqAsyncTask;


public class WifiPopUpActivity extends Activity{
	private WifiManager wm;
	private ScanResult scanResult;
	private List apList;
	private List<Wifi> wifiList = new ArrayList<>();
	private ListView lv_wifilist;
	private WifiListAdapter wifiListAdapter;
	private String ipAddress;

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.popup_wifi_list);
		searchStartWifi();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		ipAddress = getIntent().getStringExtra("ipAddress");
	}

	//wifi 초기화 및 검색 start
	public void searchStartWifi(){
		//WifiManager 초기화
		wm = (WifiManager) getSystemService(WIFI_SERVICE);

		boolean checkWifi = wm.isWifiEnabled();

		Log.d("-진우-", "checkWifi : "+checkWifi);
		if(!checkWifi){
			wm.setWifiEnabled(true);
		}

		//검색 하기
		wm.startScan();
		IntentFilter filter = new IntentFilter();
		filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		registerReceiver(wifiReceiver, filter);
	}

	//wifi 검색 완료 receiver
	private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
				searchWifi();
			}
		}
	};

	//wifi List view 셋팅
	public void searchWifi() {
		unregisterReceiver(wifiReceiver);    //리시버 해제
		apList = wm.getScanResults();
		if (wm.getScanResults() != null) {
			int size = apList.size();
			for (int i = 0; i < size; i++) {
				scanResult = (ScanResult) apList.get(i);
				wifiList.add(new Wifi(scanResult.SSID,scanResult.capabilities));
			}
		}

		lv_wifilist = (ListView)findViewById(R.id.list_wifi);
		wifiListAdapter = new WifiListAdapter(this, wifiList, R.layout.list_wifi);
		lv_wifilist.setAdapter(wifiListAdapter);

		lv_wifilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Wifi wifi = (Wifi) wifiListAdapter.getItem(position);
				String capabilities = wifi.getCapabilities();
				if (capabilities.contains("WEP") || capabilities.contains("WPA") || capabilities.contains("WPA2") || capabilities.contains("OPEN")) {
					PopUpActivity.activity = null;
					PopUpActivity.wifiPopUpActivity = WifiPopUpActivity.this;
					Intent i = new Intent(getApplicationContext(), PopUpActivity.class);
					i.putExtra("ipAddress", ipAddress);
					i.putExtra("ssid", wifi.getSsid());
					i.putExtra("capabilities", capabilities);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				} else {
					SendMessageThread sendMessageThread = new SendMessageThread(true, 0, ipAddress, wifi.getSsid(), capabilities);
					sendMessageThread.start();
				}
			}
		});
	}

	//기기에 데이터 전송 쓰레드 (기기에서 데이터를 수신하는대 시간이 걸려서 쓰레드 사용)
	class SendMessageThread extends Thread {
		private ProgressDialog dialog = new ProgressDialog(WifiPopUpActivity.this);
		private boolean isPlay = false;
		private int i;
		private String ipAddress;
		private String ssid;
		private String capabilities;

		public SendMessageThread(boolean isPlay, int i, String ipAddress, String ssid, String capabilities) {
			this.i = i;
			this.isPlay = isPlay;
			this.ipAddress = ipAddress;
			this.ssid = ssid;
			this.capabilities = capabilities;
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
					new EqAsyncTask().execute("192.168.4.1:80", "SSID", ssid+"**");
				}else if(i==1){
					new EqAsyncTask().execute("192.168.4.1:80", "PW", "**");
				}else if(i==2){
					new EqAsyncTask().execute("192.168.4.1:80", "END_SERVER", "END_SERVER");
				}else{
					connectWifi(ssid, "", capabilities);
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

	public boolean connectWifi(String ssid, String password, String capabilities) {
		Log.d("-진우-", "ssid: " + ssid+",password: "+password+",capablities: "+capabilities);
		WifiConfiguration wfc = new WifiConfiguration();

		wfc.SSID = "\"".concat( ssid ).concat("\"");
		wfc.status = WifiConfiguration.Status.DISABLED;
		wfc.priority = 40;

		if(capabilities.contains("WEP") == true ){
			Log.d("-진우-", "WEP 셋팅");
			wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
			wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
			wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
			wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
			wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);

			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
			wfc.wepKeys[0] = "\"".concat(password).concat("\"");
			wfc.wepTxKeyIndex = 0;
		}else if(capabilities.contains("WPA") == true ) {
			Log.d("-진우-", "WPA 셋팅");
			wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
			wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
			wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
			wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			wfc.preSharedKey = "\"".concat(password).concat("\"");
			Log.d("-진우-", "패스워드 확인 : " + wfc.preSharedKey);
		}else if(capabilities.contains("WPA2") == true ) {
			Log.d("-진우-", "WPA2 셋팅");
			wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
			wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
			wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
			wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			wfc.preSharedKey = "\"".concat(password).concat("\"");
		}else if(capabilities.contains("OPEN") == true ) {
			Log.d("-진우-", "OPEN 셋팅");
			wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
			wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
			wfc.allowedAuthAlgorithms.clear();
			wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);

			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		}

		int networkId = -1;
		List<WifiConfiguration> networks = wm.getConfiguredNetworks();
		Log.d("-진우-", "networks : " + networks.toString());

		for(int i=0; i<networks.size(); i++){
			Log.d("-진우-", "networks.get(i).SSID : " + networks.get(i).SSID);
			if(networks.get(i).SSID.equals("\"".concat( ssid ).concat("\""))){
				Log.d("-진우-", "networks.get(i).networkId : " + networks.get(i).networkId);
				networkId = networks.get(i).networkId;
			}
		}
		if(networkId == -1) {
			networkId = wm.addNetwork(wfc);
		}
		Log.d("-진우-", "networkId : " + networkId);

		boolean connection = false;

		if(networkId != -1){
			//Toast.makeText(getApplicationContext(), R.string.wifiPopUpActivity_settingHW, Toast.LENGTH_SHORT).show();
			connection = wm.enableNetwork(networkId, true);
			Log.d("-진우-", "connection : "+connection);
		}else{
			//Toast.makeText(getApplicationContext(), R.string.wifiPopUpActivity_reSettingHWPW, Toast.LENGTH_SHORT).show();
		}

		if(connection==true) {
			wm.setWifiEnabled(true);
			//Toast.makeText(getApplicationContext(), R.string.wifiPopUpActivity_successSettingHW, Toast.LENGTH_SHORT).show();
		}else{
			//Toast.makeText(getApplicationContext(), R.string.wifiPopUpActivity_reSettingHWConnection, Toast.LENGTH_SHORT).show();
		}

		finish();

		return true;
	}
}
