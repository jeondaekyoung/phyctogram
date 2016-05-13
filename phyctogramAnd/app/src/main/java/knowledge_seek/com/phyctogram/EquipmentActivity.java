package knowledge_seek.com.phyctogram;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.domain.Wifi;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.WifiListAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;

/**
 * Created by dkfka on 2015-12-02.
 */
public class EquipmentActivity extends BaseActivity {

    ScanResult scanResult;
    WifiManager wm;
    List apList;

    //레이아웃정의 - 슬라이드메뉴
    private LinearLayout ic_screen;
    private ImageButton btn_left;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름
    private Button btn_connWifi;
    private List<Wifi> wifiList = new ArrayList<>();
    private ListView lv_wifilist;
    private WifiListAdapter wifiListAdapter;
    private ImageView img_btn;

    //레이아웃정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_equipment, ic_screen, true);

        //test
        img_btn = (ImageView) findViewById(R.id.img_btn);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTaskCall();
                /*String url = "http://www.phyctogram.com/admin/index.do";
                String result = requestHttpGet(url);

                //test
                Toast.makeText(getApplicationContext(), "member_seq  url: "+url+" ,  result: "+result, Toast.LENGTH_LONG).show();*/
            }
        });

        //슬라이드 내 이미지, 셋팅
        img_profile = (CircularImageView) findViewById(R.id.img_profile);
        if (memberImg != null) {
            img_profile.setImageBitmap(memberImg);
        }

        //기기 검색 버튼 셋팅
        btn_connWifi = (Button) findViewById(R.id.connWifiBtn);
        btn_connWifi.setText(R.string.equipmentActivity_searchHW);
        btn_connWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_connWifi.setText(R.string.equipmentActivity_searching);
                searchStartWifi();
            }
        });

        //슬라이드 내 이름, 셋팅
        tv_member_name = (TextView) findViewById(R.id.tv_member_name);
        if (memberName != null) {
            tv_member_name.setText(memberName);
        }

        //슬라이드 내 아이 목록(ListView)에서 아이 선택시
        lv_usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*nowUsers = (Users) usersListSlideAdapter.getItem(position);
                Log.d("-진우-", "선택한 아이 : " + nowUsers.toString());
                Toast.makeText(getApplicationContext(), "'" + nowUsers.getName() + "' 아이를 선택하였습니다", Toast.LENGTH_LONG).show();*/
            }
        });
        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });
    }

    //wifi 초기화 및 검색 start
    public void searchStartWifi(){
        //WifiManager wfMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        /*boolean checkWifi = wfMgr.isWifiEnabled();
        Log.d("-진우-", "checkWifi : "+checkWifi);
        if(!checkWifi){
            wfMgr.setWifiEnabled(true);
        }*/

        //롤리팝 버전 이상이라면 권한 체크 요청함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                //requestPermissions API 23이상에서 사용 가능 오류 아님
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
            }catch (Exception e){
                Log.d("-진우-","ParingActivity requestPermissions Exception : "+ e.getMessage());
            }
        }else{
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
    }
    //권한 체크 결과 받음
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 11) {
            //허용 0, 비허용 -1
            if (grantResults[0] == 0){
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
            }else{
                Log.d("-진우-", "fail");
            }
        }
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

        lv_wifilist = (ListView)findViewById(R.id.lv_wifiList);
        wifiListAdapter = new WifiListAdapter(this, wifiList, R.layout.list_wifi);
        lv_wifilist.setAdapter(wifiListAdapter);

        lv_wifilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wifi wifi = (Wifi) wifiListAdapter.getItem(position);
                String capabilities = wifi.getCapabilities();
                if(capabilities.contains("WEP")||capabilities.contains("WPA")||capabilities.contains("WPA2")||capabilities.contains("OPEN")){
                    openPopup(wifi.getSsid(), wifi.getCapabilities());
                }else{
                    connectWifi(wifi.getSsid(), "", wifi.getCapabilities());
                }
            }
        });

        btn_connWifi.setText(R.string.equipmentActivity_endSearch);
    }

    private Button btnClosePopup, btnPwdOk;
    private PopupWindow pwindo;
    private int mWidthPixels, mHeightPixels;
    private TextView tv_ssid, textCapabilities;
    private EditText textPassword;

    public void openPopup(String ssid, String capabilities){

        PopUpActivity.activity = this;

        Intent i = new Intent(getApplicationContext(), PopUpActivity.class);
        i.putExtra("ssid", ssid);
        i.putExtra("capabilities", capabilities);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        /*WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        // 상태바와 메뉴바의 크기를 포함해서 재계산
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }

        try {
            //  LayoutInflater 객체와 시킴
            LayoutInflater inflater = (LayoutInflater) EquipmentActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup_wifi,
                    (ViewGroup) findViewById(R.id.popup_element));

            pwindo = new PopupWindow(layout, mWidthPixels-200, mHeightPixels-500, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);
            btnPwdOk = (Button) layout.findViewById(R.id.btn_pwdOk);
            btnPwdOk.setOnClickListener(ok_button_click_listener);
            *//*textSsid = (TextView) layout.findViewById(R.id.text_ssid);
            textSsid.setText(ssid);*//*
            *//*textCapabilities = (TextView) layout.findViewById(R.id.text_capabilities);
            textCapabilities.setText(capabilities);*//*
            textPassword = (EditText) layout.findViewById(R.id.edit_password);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /*private View.OnClickListener ok_button_click_listener =
        new View.OnClickListener() {

            public void onClick(View v) {
                connectWifi(tv_ssid.getText().toString(), textPassword.getText().toString(), textCapabilities.getText().toString());
                pwindo.dismiss();
            }
    };

    private View.OnClickListener cancel_button_click_listener =
        new View.OnClickListener() {

            public void onClick(View v) {
                pwindo.dismiss();
            }
    };*/

    public boolean connectWifi(String ssid, String password, String capabilities) {
        Log.d("-진우-", "ssid: " + ssid + ",password: " + password + ",capablities: " +capabilities);
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

        Log.d("-진우-", "wfc : " + wfc.toString());

        /*int networkId = wm.addNetwork(wfc);
        if(networkId != -1){
            wm.enableNetwork(networkId, true);
            Log.d("-진우-", "연결됬나?");
        }
*/
        int networkId = -1;
        //WifiManager wfMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
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
            Toast.makeText(getApplicationContext(), R.string.equipmentActivity_connectionAlert, Toast.LENGTH_SHORT).show();
            //wfMgr.enableNetwork(networkId, true);
            connection = wm.enableNetwork(networkId, true);
            Log.d("-진우-", "connection : "+connection);
        }else{
            Toast.makeText(getApplicationContext(), R.string.equipmentActivity_failPW, Toast.LENGTH_SHORT).show();
        }

        if(connection==true) {
            wm.setWifiEnabled(true);
            Toast.makeText(getApplicationContext(), R.string.equipmentActivity_successConnection, Toast.LENGTH_SHORT).show();

            WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
            DhcpInfo dhcpInfo = wm.getDhcpInfo() ;
            int serverIp = dhcpInfo.gateway;

            String ipAddress = String.format(
                    "%d.%d.%d.%d",
                    (serverIp & 0xff),
                    (serverIp >> 8 & 0xff),
                    (serverIp >> 16 & 0xff),
                    (serverIp >> 24 & 0xff));

            Log.d("-진우-", "ipAddress: " + ipAddress);
            Log.d("-진우-", "member.getMember_seq(): " + member.getMember_seq());
            String url = "http://"+ipAddress+":80?member_seq="+member.getMember_seq()+"**";
            Log.d("-진우-", "url: " + url);
            //String result = sendData(url);

            //test
            //Toast.makeText(getApplicationContext(), "member_seq  url: "+url+" ,  result: "+result, Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), WifiPopUpActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("ipAddress", ipAddress);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), R.string.equipmentActivity_failConnection, Toast.LENGTH_SHORT).show();
        }
        btn_connWifi.setText(R.string.equipmentActivity_endSearch);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "EquipmentActivity.onResume() 실행");

        //슬라이드메뉴 셋팅
        initSildeMenu();

        //슬라이드메뉴 내 아이 목록 셋팅
        usersListSlideAdapter.setUsersList(usersList);
        int height = getListViewHeight(lv_usersList);
        lv_usersList.getLayoutParams().height = height;
        usersListSlideAdapter.notifyDataSetChanged();

        //슬라이드메뉴 셋팅(내 아이목록, 계정이름, 계정이미지)
        //EquipmentTask task = new EquipmentTask();
        //task.execute(img_profile);

        Log.d("-진우-", "EquipmentActivity 에서 onResume() : " + member.toString());

        Log.d("-진우-", "EquipmentActivity.onResume() 끝");
    }

    //기록조회페이지 초기 데이터조회(슬라이드 내 아이 목록, 계정이미지)
    private class EquipmentTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(EquipmentActivity.this);
        private List<Users> usersTask;
        private CircularImageView img_profileTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getString(R.string.commonActivity_wait));
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            Bitmap mBitmap = null;
            img_profileTask = (CircularImageView)params[0];

            //슬라이드메뉴에 있는 내 아이 목록
            /*UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }*/

            /*String image_url = null;
            if(member.getJoin_route().equals("kakao")){
                image_url = member.getKakao_thumbnailimagepath();
                //이미지 불러오기
                InputStream in = null;
                try {
                    Log.d("-진우-", "이미지 주소 : " + image_url);
                    in = new URL(image_url).openStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else if(member.getJoin_route().equals("facebook")){
                image_url = "http://graph.facebook.com/" + member.getFacebook_id() + "/picture?type=large";
                //이미지 불러오기
                InputStream in = null;
                try {
                    //페이스북은 jpg파일이 링크 걸린 것이 아니다.
                    //http://graph.facebook.com/userid/picture?type=large
                    Log.d("-진우-", "이미지 주소 : " + image_url);

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(image_url)
                            .build();
                    com.squareup.okhttp.Response response = client.newCall(request).execute();
                    in = response.body().byteStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }*/

            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            /*if(bitmap != null){
                Log.d("-진우-", "이미지읽어옴");
                img_profileTask.setImageBitmap(bitmap);
            }

            if (usersTask != null && usersTask.size() > 0) {
                Log.d("-진우-", "내 아이는 몇명? " + usersTask.size());
                for (Users u : usersTask) {
                    Log.d("-진우-", "내 아이 : " + u.toString());
                }
                usersList = usersTask;

                usersListSlideAdapter.setUsersList(usersList);
                if (nowUsers == null) {
                    nowUsers = usersTask.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();*/

            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }

    public void asyncTaskCall() {
        String ip = "192.168.123.254";
        new TestAsyncTask().execute(ip);
    }

    private class TestAsyncTask extends AsyncTask<Object, Integer, Void>{
        // doInBackground 메소드가 실행되기 전에 실행되는 메소드
        @Override
        protected void onPreExecute() {
            // UI 작업을 수행하는 부분
            super.onPreExecute();
        }

        // 실제 비즈니스 로직이 처리될 메소드(Thread 부분이라고 생각하면 됨)
        @Override
        protected Void doInBackground(Object... params) {
            String ip = (String) params[0];
            Log.d("-진우-", "ip : "+ip);
            try {
                Log.d("-진우-", "Exception : 1");
                URL reqUrl = new URL("http://192.168.4.1:80/");
                Log.d("-진우-", "Exception : 2");
                HttpURLConnection urlConn = (HttpURLConnection) reqUrl.openConnection();
                Log.d("-진우-", "Exception : 3");
                urlConn.setRequestMethod("GET");
                Log.d("-진우-", "Exception : 4");
                urlConn.setRequestProperty("REF", "180**");
                Log.d("-진우-", "Exception : 5");

                int resCode = urlConn.getResponseCode();
                Log.d("-진우-", "Exception : 6");
                if (resCode != HttpURLConnection.HTTP_OK) return null;
                Log.d("-진우-", "Exception : 7");

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                Log.d("-진우-", "Exception : 8");
                String input;
                Log.d("-진우-", "Exception : 9");
                StringBuffer sb = new StringBuffer();
                Log.d("-진우-", "Exception : 10");

                while ((input = reader.readLine()) != null){
                    sb.append(input);
                }
            }catch (Exception e){
                Log.d("-진우-", "Exception : " + e.getMessage());
            }
            return null;
        }

        // 모든 작업이 끝난 후 처리되는 메소드
        @Override
        protected void onPostExecute(Void result) {
            //Log.d("-진우-", "result : " + result.toString());
            super.onPostExecute(result);
        }
    }
}