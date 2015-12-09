package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.UsersListAdapter;
import knowledge_seek.com.phyctogram.listAdapter.UsersListSlideAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by dkfka on 2015-11-25.
 */
public class MainActivity extends BaseActivity {
    public static final String HTTPADDR = "http://117.52.89.181";

    //데이터
    private Member member;
    private List<Users> usersList = null;
    private Users users;

    /* slide menu */
    public static Button bt_left;


    //정의
    private Button btn_usermanage;
    private ListView lv_userslist;
    private UsersListSlideAdapter usersListSlideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            member = (Member)bundle.getSerializable("member");
            Log.d("-진우-", "MainActivity 에서 onCreate() :  " + member.toString());
        } else {
            member = new Member();
        }

        //사이드매뉴
        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        initSildeMenu();


        //정의
        btn_usermanage = (Button)findViewById(R.id.btn_usermanage);
        btn_usermanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UsersManageActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
            }
        });
        lv_userslist = (ListView)findViewById(R.id.lv_userslist);
        usersListSlideAdapter = new UsersListSlideAdapter(this);
        lv_userslist.setAdapter(usersListSlideAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("-진우-", "MainActivity 에서 onResume() : " + member.toString());
        updateUsersList();

    }

    //화면 업데이트
    private void updateUsersList(){

        /*UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
        Call<List<Users>> call = service.findByMember(String.valueOf(member.getMember_seq()));
        try {
            usersList = call.execute().body();
        } catch (IOException e){
            Log.d("-진우-", "내 아이 목록 가져오기 실패");
        }

        if(usersList != null){
            for(Users u : usersList){
                Log.d("-진우-", "내아이 : " + u.toString());
            }
            usersListSlideAdapter.setUsersList(usersList);
        } else {
            Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
        }

        int height = getListViewHeight(lv_userslist);
        lv_userslist.getLayoutParams().height = height;*/

        BackgroundTask task = new BackgroundTask();
        task.execute();
        usersListSlideAdapter.notifyDataSetChanged();

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPADDR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI service = retrofit.create(UsersAPI.class);
        final Call<List<Users>> call = service.findByMember(String.valueOf(member.getMember_seq()));
        new Thread(){
            @Override
            public void run() {
                super.run();

                try{
                    usersList = call.execute().body();

                    if(usersList != null){
                        for(Users u : usersList){
                            Log.d("-진우-", "내아이 : " + u.toString());
                        }
                        usersListSlideAdapter.setUsersList(usersList);
                    } else {
                        Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("-진우-", "유저는 몇명 ? " + usersList.size());
                        int height = getListViewHeight(lv_userslist);
                        lv_userslist.getLayoutParams().height = height;

                        usersListSlideAdapter.notifyDataSetChanged();
                    }
                });

            }
        }.start();*/
    }


    /*
    * 리스트뷰의 높이를 구함
    * */
    private int getListViewHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        int listViewHeight = 0;
        listView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        listViewHeight = listView.getMeasuredHeight() * adapter.getCount() + (adapter.getCount() * listView.getDividerHeight());
        return listViewHeight;
    }

    private class BackgroundTask extends AsyncTask<Void, Void, List<Users>>{
        private List<Users> usersTask;

        @Override
        protected List<Users> doInBackground(Void... params) {
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
            Call<List<Users>> call = service.findByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            return usersTask;
        }

        @Override
        protected void onPostExecute(List<Users> userses) {
            if(userses != null){
                for(Users u : userses){
                    Log.d("-진우-", "내아이 : " + u.toString());
                }
                usersListSlideAdapter.setUsersList(userses);
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }

            usersList = userses;

            int height = getListViewHeight(lv_userslist);
            lv_userslist.getLayoutParams().height = height;
        }
    }
}