package knowledge_seek.com.phyctogram;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Member;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.UsersListAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dkfka on 2015-12-07.
 */
public class UsersManageActivity extends BaseActivity {
    public static final String HTTPADDR = "http://117.52.89.181";

    //데이터
    private Member member;
    private List<Users> usersList = null;

    /* slide menu */
    public static Button bt_left;

    //정의
    private Button btn_usersadd;
    private ListView lv_userslist;
    private UsersListAdapter usersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_manage);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            member = (Member) bundle.getSerializable("member");
            Log.d("-진우-", "UsersManageActivity 에서 " + member.toString());
        }

        //사이드 메뉴
        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });
        initSildeMenu();

        //정의
        btn_usersadd = (Button)findViewById(R.id.btn_usersadd);
        btn_usersadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UsersAddActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
            }
        });
        lv_userslist = (ListView) findViewById(R.id.lv_userslist);
        usersListAdapter = new UsersListAdapter(this);

        lv_userslist.setAdapter(usersListAdapter);

        //리스트뷰 클릭 -> 내 아이 수정으로 이동
        lv_userslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users users = (Users) usersListAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), UsersModActivity.class);
                intent.putExtra("users", users);
                startActivity(intent);
            }
        });

        //리스트뷰 롱클릭 -> 내 아이 삭제됨
        lv_userslist.setLongClickable(true);
        lv_userslist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Users users = (Users)usersListAdapter.getItem(position);

                //Log.d("-진우-", "삭제 : " + users.getName());
                AlertDialog.Builder dialog = new AlertDialog.Builder(UsersManageActivity.this);
                dialog.setTitle("삭제");
                dialog.setMessage("아이를 삭제할 경우 아이의 키도 삭제됩니다. " +
                        "아이를 삭제하시겠습니까?");
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(HTTPADDR)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        UsersAPI service = retrofit.create(UsersAPI.class);
                        Call<String> call = service.delUsersByUserSeq(String.valueOf(users.getUser_seq()));
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                Log.d("-진우-", "내아이 삭제 성공 결과1 : " + response.body());
                                updateUsersList();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.d("-진우-", "내아이를 삭제하는 실패하였습니다. - " + t.getMessage() + ", " + t.getCause() + ", " + t.getStackTrace());
                            }
                        });
                    }
                });
                dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            }
        });



    }


    @Override
    protected void onResume(){
        super.onResume();

        updateUsersList();

    }

    //화면 업데이트
    private void updateUsersList(){
        Retrofit retrofit = new Retrofit.Builder()
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
                        usersListAdapter.setUsersList(usersList);
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

                        usersListAdapter.notifyDataSetChanged();
                    }
                });

            }
        }.start();
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
}
