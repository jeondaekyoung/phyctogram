package knowledge_seek.com.phyctogram;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.UsersListManageAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dkfka on 2015-12-07.
 */
public class UsersManageActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의
    private Button btn_usersadd;
    private ListView lv_usersList_manage;
    private UsersListManageAdapter usersListManageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout) findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_users_manage, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        //레이아웃정의
        btn_usersadd = (Button) findViewById(R.id.btn_usersadd);
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
                final Users users = (Users) usersListManageAdapter.getItem(position);

                //Log.d("-진우-", "삭제 : " + users.getName());
                AlertDialog.Builder dialog = new AlertDialog.Builder(UsersManageActivity.this);
                dialog.setTitle("삭제");
                dialog.setMessage("아이를 삭제할 경우 아이의 키도 삭제됩니다. " +
                        "아이를 삭제하시겠습니까?");
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
                        Call<String> call = service.delUsersByUserSeq(String.valueOf(users.getUser_seq()));
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                Log.d("-진우-", "내아이 삭제 성공 결과1 : " + response.body());
                                Intent intent = new Intent(getApplicationContext(), UsersManageActivity.class);
                                intent.putExtra("member", member);
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
    protected void onResume() {
        super.onResume();

        //슬라이드메뉴에 있는 내 아이 목록
        updateScreenSlide();

        FindUsersByMemberTask1 task = new FindUsersByMemberTask1();
        task.execute();

    }


    //내아이 목록가져오기
    private class FindUsersByMemberTask1 extends AsyncTask<Void, Void, List<Users>> {
        private List<Users> usersTask;
        private ProgressDialog dialog = new ProgressDialog(UsersManageActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected List<Users> doInBackground(Void... params) {
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class);
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패(내아이관리)");
            }
            return usersTask;
        }

        @Override
        protected void onPostExecute(List<Users> userses) {
            if (userses != null && userses.size() > 0) {
                for (Users u : userses) {
                    Log.d("-진우-", "내아이 : " + u.toString());
                }
                usersListManageAdapter.setUsersList(userses);
                usersList = userses;
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다.");
            }

            int height = getListViewHeight(lv_usersList_manage);
            lv_usersList_manage.getLayoutParams().height = height;
            usersListManageAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(userses);
        }

    }


}