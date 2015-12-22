package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Height;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.HeightAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;

/**
 * Created by dkfka on 2015-12-10.
 */
public class UsersDataInputActivity extends BaseActivity {

    //데이터
    private Height usersHeight;

    //레이아웃정의
    private LinearLayout ic_screen;
    private ImageButton btn_left;
    private EditText et_input_height;       //키
    private Button btn_users_height;       //키 저장
    private TextView tv_users_name;     //아이 이름 출력

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout) findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_users_data_input, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //데이터셋팅
        usersHeight = new Height();

        //슬라이드 내 아이 목록(ListView)에서 아이 선택시
        tv_users_name = (TextView) findViewById(R.id.tv_users_name);
        lv_usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nowUsers = (Users)usersListSlideAdapter.getItem(position);
                Log.d("-진우-", "선택한 아이 : " + nowUsers.toString());
                Toast.makeText(getApplicationContext(), "'" + nowUsers.getName() + "' 아이를 선택하였습니다", Toast.LENGTH_LONG).show();

                if(tv_users_name != null){
                    tv_users_name.setText(nowUsers.getName());
                }

                //메인페이지에 출력할 아이에 관한 데이터(분석포함)를 가져와야한다.
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
        et_input_height = (EditText)findViewById(R.id.et_input_height);

        //키 저장
        btn_users_height = (Button)findViewById(R.id.btn_users_height);
        btn_users_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String height_str = et_input_height.getText().toString();
                Log.d("-진우-", "입력된 키 : " + height_str);
                if(!checkHeight(height_str)){
                    return ;
                }

                double height = Double.valueOf(height_str);
                usersHeight.setUser_seq(nowUsers.getUser_seq());
                usersHeight.setHeight(height);

                Log.d("-진우-", "키 저장하기 : " + usersHeight.toString());
                Log.d("-진우-", "json : " + Utility.height2json(usersHeight));

                RegisterHeightTask task = new RegisterHeightTask(usersHeight);
                task.execute();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //요건되는데, BaseActivity.onResume()에 있으면 안되네..
        //login, join등의 member이 없는 activity가 있기 때문에 안된다.
        //슬라이드메뉴에 있는 내 아이 목록
        //updateScreenSlide();
        UsersDataInputDataTask task = new UsersDataInputDataTask();
        task.execute();

        Log.d("-진우-", "UsersDataInputActivity 에 onResume() : " + member.toString());
        //Log.d("-진우-", "UsersDataInputActivity 에 onResume() : " + nowUsers.toString());
    }

    //height 내용 체크
    private boolean checkHeight(String height_str){
        //Log.d("-진우-", "자릿수 : " + height_str.length());
        if(height_str.length() <= 0 || height_str.length() > 7){
            Toast.makeText(getApplicationContext(), "키를 확인해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //키 저장하기
    private class RegisterHeightTask extends AsyncTask<Void, Void, String>{

        private Height heightTask;
        private ProgressDialog dialog = new ProgressDialog(UsersDataInputActivity.this);

        public RegisterHeightTask(Height height){
            this.heightTask = height;
        }

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            HeightAPI service = ServiceGenerator.createService(HeightAPI.class, "Height");
            Call<String> call = service.registerHeight(heightTask);
            try {
                result = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "키 저장 실패");
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result != null && result.equals("success")){
                Toast.makeText(getApplicationContext(), "저장하였습니다", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("-진우-", "저장하는데 실패하였습니다");
            }

            dialog.dismiss();
            super.onPostExecute(result);
        }
    }

    //직접입력페이지 초기 데이터조회(슬라이드 내 아이 목록)
    private class UsersDataInputDataTask extends AsyncTask<Void, Void, List<Users>> {

        private ProgressDialog dialog = new ProgressDialog(UsersDataInputActivity.this);
        private List<Users> usersTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected List<Users> doInBackground(Void... params) {
            //슬라이드메뉴에 있는 내 아이 목록
            //updateScreenSlide();  //내 아이 목록을 가져오기전에 MainDataTask가 끝난다.
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            return usersTask;
        }

        @Override
        protected void onPostExecute(List<Users> userses) {
            if (userses != null && userses.size() > 0) {
                Log.d("-진우-", "내 아이는 몇명? " + userses.size());
                for (Users u : userses) {
                    Log.d("-진우-", "내아이 : " + u.toString());
                }
                usersList = userses;

                usersListSlideAdapter.setUsersList(usersList);
                if (nowUsers == null) {
                    nowUsers = userses.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());
                tv_users_name.setText(nowUsers.getName());
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(userses);
        }
    }
}
