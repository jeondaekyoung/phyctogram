package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

import knowledge_seek.com.phyctogram.domain.Comment;
import knowledge_seek.com.phyctogram.domain.SqlCommntyListView;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.CommentAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;

/**
 * Created by dkfka on 2015-12-08.
 */
public class CommunityCommentActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의
    private EditText et_content;
    private Button btn_comment_register;

    //데이터
    private SqlCommntyListView sqlCommntyListView;
    private Comment comment = new Comment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_community_comment, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            sqlCommntyListView = (SqlCommntyListView)bundle.getSerializable("sqlCommntyListView");
            Log.d("-진우-", "CommunityCommentActivity 에서 " + sqlCommntyListView.toString());
        } else {
            Log.d("-진우-", "CommunityCommentActivity 에 sqlCommntyListView가 없다");
        }

        //레이아웃 정의
        btn_left = (ImageButton)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "슬라이드 클릭", Toast.LENGTH_SHORT).show();
                menuLeftSlideAnimationToggle();
            }
        });
        et_content = (EditText)findViewById(R.id.et_content);
        btn_comment_register = (Button)findViewById(R.id.btn_comment_register);
        btn_comment_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //내용체크
                comment.setContent(et_content.getText().toString());
                comment.setMember_seq(member.getMember_seq());
                comment.setCommnty_seq(sqlCommntyListView.getCommnty_seq());

                if(!checkComment(comment)){
                    return ;
                }

                //댓글 저장하기
                Log.d("-진우-", "댓글 저장하기 : " + comment.toString());
                Log.d("-진우-", "json : " + Utility.comment2json(comment));

                //댓글 저장하기
                RegisterCommentTask task = new RegisterCommentTask(comment);
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
        updateScreenSlide();

        Log.d("-진우-", "CommunityCommentActivity 에 onResume() : " + member.toString());
    }

    //comment의 내용 체크
    private boolean checkComment(Comment comment){
        if(comment.getContent().length() <= 0){
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //댓글 쓰기
    private class RegisterCommentTask extends AsyncTask<Void, Void, String>{

        private Comment comment;
        private ProgressDialog dialog = new ProgressDialog(CommunityCommentActivity.this);

        public RegisterCommentTask(Comment comment){
            this.comment = comment;
        }

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            CommentAPI service = ServiceGenerator.createService(CommentAPI.class, "Comment");
            Call<String> call = service.registerComment(comment);
            try {
                result = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "댓글저장 실패");
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
            finish();
        }
    }
}
