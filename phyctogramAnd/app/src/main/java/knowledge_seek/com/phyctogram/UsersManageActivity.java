package knowledge_seek.com.phyctogram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.UsersListManageAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
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
    //public static final String HTTPADDR = "http://117.52.89.181";

    //레이아웃정의 - 슬라이드메뉴
    private Button btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의
    private Button btn_usersadd;
    private ListView lv_usersList_manage;
    private UsersListManageAdapter usersListManageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_users_manage, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        //레이아웃정의
        btn_usersadd = (Button)findViewById(R.id.btn_usersadd);
        btn_usersadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UsersManageActivity.this, "내아이 추가하러 가자", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), UsersAddActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
            }
        });
        lv_usersList_manage = (ListView) findViewById(R.id.lv_usersList_manage);
        usersListManageAdapter = new UsersListManageAdapter(this);
        lv_usersList_manage.setAdapter(usersListManageAdapter);

        //리스트뷰 클릭 -> 내 아이 수정으로 이동
        lv_usersList_manage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users users = (Users) usersListManageAdapter.getItem(position);

                //Log.d("-진우-", "수정할 아이 : " + users.toString());
                Intent intent = new Intent(getApplicationContext(), UsersModActivity.class);
                intent.putExtra("member", member);
                intent.putExtra("users", users);
                startActivity(intent);
                finish();
            }
        });

        //리스트뷰 롱클릭 -> 내 아이 삭제됨
        lv_usersList_manage.setLongClickable(true);
        lv_usersList_manage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Users users = (Users)usersListManageAdapter.getItem(position);

                //Log.d("-진우-", "삭제 : " + users.getName());
                AlertDialog.Builder dialog = new AlertDialog.Builder(UsersManageActivity.this);
                dialog.setTitle("삭제");
                dialog.setMessage("아이를 삭제할 경우 아이의 키도 삭제됩니다. " +
                        "아이를 삭제하시겠습니까?");
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        /*Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(HTTPADDR)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        UsersAPI service = retrofit.create(UsersAPI.class);*/
                        UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
                        Call<String> call = service.delUsersByUserSeq(String.valueOf(users.getUser_seq()));
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                Log.d("-진우-", "내아이 삭제 성공 결과1 : " + response.body());
                                Intent intent = new Intent(getApplicationContext(), UsersManageActivity.class);
                                intent.putExtra("member", member);
                                //intent.putExtra("users", users);
                                startActivity(intent);
                                finish();

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

        //슬라이드메뉴에 있는 내 아이 목록
        updateScreenSlide();

        Log.d("-진우-", "UsersManageActivity 에 onResume() : " + member.toString());
        if(usersList != null && usersList.size() > 0){
            for(Users u :usersList){
                Log.d("-진우-", "UsersManageActivity 에 onResume(), 내 아이 : " + u.toString());
            }
            //등록된 아이가 없을 경우 에러발생
            usersListManageAdapter.setUsersList(usersList);
        } else {
            usersList = new ArrayList<Users>();
            usersListManageAdapter.setUsersList(usersList);
            Log.d("-진우-", "UsersManageActivity 에 onResume(), 등록된 내 아이가 없습니다. ");
        }


        //int height = getListViewHeight(lv_usersList_manage);
        //lv_usersList_manage.getLayoutParams().height = height;
        usersListManageAdapter.notifyDataSetChanged();

    }

    //화면 업데이트
    private void updateUsersList(){

        updateScreenSlide();
        Log.d("-진우-", "UsersManageActivity 에 onResume() : " + member.toString());
        if(usersList != null && usersList.size() > 0){
            for(Users u :usersList){
                Log.d("-진우-", "UsersManageActivity 에 onResume(), 내 아이 : " + u.toString());
            }
            //등록된 아이가 없을 경우 에러발생
            usersListManageAdapter.setUsersList(usersList);
        } else {
            Log.d("-진우-", "UsersManageActivity 에 onResume(), 등록된 내 아이가 없습니다. ");
        }

        //int height = getListViewHeight(lv_usersList_manage);
        //lv_usersList_manage.getLayoutParams().height = height;
        usersListManageAdapter.notifyDataSetChanged();

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPADDR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI service = retrofit.create(UsersAPI.class);
        final Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
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
        }.start();*/
    }


}
