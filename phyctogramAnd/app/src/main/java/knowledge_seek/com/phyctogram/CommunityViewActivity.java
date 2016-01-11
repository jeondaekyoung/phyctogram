package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Comment;
import knowledge_seek.com.phyctogram.domain.Commnty;
import knowledge_seek.com.phyctogram.domain.SqlCommntyListView;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.listAdapter.CommentListAdapter;
import knowledge_seek.com.phyctogram.retrofitapi.CommentAPI;
import knowledge_seek.com.phyctogram.retrofitapi.CommntyAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import retrofit.Call;

/**
 * Created by dkfka on 2015-12-04.
 */
public class CommunityViewActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드 메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private EditText reply;

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
                //Toast.makeText(CommunityViewActivity.this, "댓글쓰기로 가자", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CommunityCommentActivity.class);
                intent.putExtra("member", member);
                intent.putExtra("sqlCommntyListView", sqlCommntyListView);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateScreenSlide();

        Log.d("-진우-", "CommunityViewActivity 에 onResume() : " + member.toString());

        FindCommntyAndCommentTask task = new FindCommntyAndCommentTask(sqlCommntyListView.getCommnty_seq());
        task.execute();
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