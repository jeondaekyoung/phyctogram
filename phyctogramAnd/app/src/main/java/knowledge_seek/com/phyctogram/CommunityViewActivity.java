package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Comment;
import knowledge_seek.com.phyctogram.domain.Commnty;
import knowledge_seek.com.phyctogram.domain.SqlCommntyListView;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.CommentListAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.CommentAPI;
import knowledge_seek.com.phyctogram.retrofitapi.CommntyAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;

/**
 * Created by dkfka on 2015-12-04.
 */
public class CommunityViewActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드 메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //레이아웃 정의
    private TextView tv_title;              //제목
    private TextView tv_name;            //이름
    private TextView tv_writng_de;     //작성날짜
    private TextView tv_hits_co;         //조회수
    private TextView tv_cnt;               //댓글수
    private TextView tv_contents;       //내용
    private ListView lv_comments;        //댓글 리스트
    private CommentListAdapter commentListAdapter;
    private EditText et_comment;        //댓글 쓰기

    //데이터정의
    private SqlCommntyListView sqlCommntyListView = new SqlCommntyListView();
    private Commnty commnty = new Commnty();
    private List<Comment> CommentList = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout) findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_community_view, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            sqlCommntyListView = (SqlCommntyListView)bundle.getSerializable("sqlCommntyListView");
            Log.d("-진우-", "CommunityViewActivity 에서 " + sqlCommntyListView.toString());
        } else {
            Log.d("-진우-", "CommunityViewActivity 에 sqlCommntyListView가 없다.");
        }

        //슬라이드 내 이미지
        img_profile = (CircularImageView) findViewById(R.id.img_profile);
        //슬라이드 내 이름
        tv_member_name = (TextView) findViewById(R.id.tv_member_name);
        //슬라이드 내 아이 목록(ListView)에서 아이 선택시
        lv_usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*nowUsers = (Users)usersListSlideAdapter.getItem(position);
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

        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText(sqlCommntyListView.getTitle());
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_name.setText(sqlCommntyListView.getName());
        tv_writng_de = (TextView)findViewById(R.id.tv_writng_de);
        tv_writng_de.setText(sqlCommntyListView.getWritng_de());
        tv_hits_co = (TextView)findViewById(R.id.tv_hits_co);
        tv_hits_co.setText(String.valueOf(sqlCommntyListView.getHits_co()));
        tv_cnt = (TextView)findViewById(R.id.tv_cnt);
        tv_cnt.setText(new StringBuilder().append("댓글 ").append(sqlCommntyListView.getCnt()).append(" 개").toString());
        tv_contents = (TextView)findViewById(R.id.tv_contents);


        //댓글 목록
        lv_comments = (ListView)findViewById(R.id.lv_comments);
        commentListAdapter = new CommentListAdapter(this, CommentList, R.layout.list_comment);
        lv_comments.setAdapter(commentListAdapter);

        //댓글쓰기로 가기
        et_comment = (EditText)findViewById(R.id.et_comment);
        et_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CommunityCommentActivity.class);
                intent.putExtra("member", member);
                intent.putExtra("sqlCommntyListView", sqlCommntyListView);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "CommunityViewActivity.onResume() 실행");

        //슬라이드메뉴 셋팅(내 아이 목록, 계정이미지)
        CommunityViewTask task = new CommunityViewTask();
        task.execute(img_profile);

        Log.d("-진우-", "CommunityViewActivity 에 onResume() : " + member.toString());

        //슬라이드메뉴 계정이름 셋팅
        String name = null;
        if (member.getJoin_route().equals("kakao")) {
            name = member.getKakao_nickname() + " 님";
        } else if (member.getJoin_route().equals("facebook")) {
            name = member.getFacebook_name() + " 님";
        } else {
            name = member.getName() + " 님";
        }
        if (name != null) {
            tv_member_name.setText(name);
        }

        FindCommntyAndCommentTask task1 = new FindCommntyAndCommentTask(sqlCommntyListView.getCommnty_seq());
        task1.execute();

        Log.d("-진우-", "CommunityViewActivity.onResume() 끝");
    }

    //수다방글보기페이지 초기 데이터조회(슬라이드 내 아이 목록, 내 이미지)
    private class CommunityViewTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(CommunityViewActivity.this);
        private List<Users> usersTask;
        private CircularImageView img_profileTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            Bitmap mBitmap = null;
            img_profileTask = (CircularImageView) params[0];

            //슬라이드메뉴에 있는 내 아이 목록
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Users");
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            //이미지 불러오기
            String image_url = null;
            if (member.getJoin_route().equals("kakao")) {
                image_url = member.getKakao_thumbnailimagepath();
                InputStream in = null;
                try {
                    Log.d("-진우-", "이미지 주소 : " + image_url);
                    in = new URL(image_url).openStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (member.getJoin_route().equals("facebook")) {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
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
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }

    //수다방 내용 읽어오기
    private class FindCommntyAndCommentTask extends AsyncTask<Void, Void, Void>{

        private int commnty_seqTask;
        private Commnty commntyTask;
        private List<Comment> commentListTask = new ArrayList<Comment>();
        private ProgressDialog dialog = new ProgressDialog(CommunityViewActivity.this);

        public FindCommntyAndCommentTask(int commnty_seqTask) {
            this.commnty_seqTask = commnty_seqTask;
        }

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            CommntyAPI service = ServiceGenerator.createService(CommntyAPI.class, "CommunityViewActivity");
            Call<Commnty> call = service.findCommntyByCommntySeq(commnty_seqTask);
            try {
                commntyTask = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "수다방 조회 실패");
            }

            CommentAPI service1 = ServiceGenerator.createService(CommentAPI.class, "Comment");
            Call<List<Comment>> call1 = service1.findCommentByCommntySeq(commnty_seqTask);
            try {
                commentListTask = call1.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "수다방 댓글 조회 실패");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(commntyTask != null){
                Log.d("-진우-", "읽어온 커뮤니티 : " + commntyTask.toString());
                commnty = commntyTask;
                tv_contents.setText(commnty.getContents());
            } else {
                Log.d("-진우-", "읽어온 커뮤니티가 없습니다");
            }
            if(commentListTask != null && commentListTask.size() > 0){
                for(Comment c : commentListTask){
                    Log.d("-진우-", "목록 : " + c.toString());
                }
                Log.d("-진우-", "읽어온 목록은 " + commentListTask.size() + " 개 있습니다");

                CommentList = commentListTask;
                commentListAdapter.setComments(commentListTask);

            }

            int height = getListViewHeight(lv_comments);
            lv_comments.getLayoutParams().height = height;

            commentListAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }
}