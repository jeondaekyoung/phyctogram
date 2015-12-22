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

import knowledge_seek.com.phyctogram.domain.Commnty;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.CommntyAPI;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.util.Utility;
import retrofit.Call;

/**
 * Created by dkfka on 2015-11-26.
 */
public class CommunityWriteActivity extends BaseActivity {

    //레이아웃정의 - 슬라이드메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;

    //레이아웃정의
    private Button btn_community_save;
    private EditText et_title;
    private EditText et_contents;

    //데이터정의
    private Commnty commnty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_commuinity_write);

        //화면 페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_commuinity_write, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //레이아웃 정의
        btn_left = (ImageButton)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });
        et_title = (EditText)findViewById(R.id.et_title);
        et_contents = (EditText)findViewById(R.id.et_contents);
        //수다방(커뮤니티) 글 저장
        btn_community_save = (Button)findViewById(R.id.btn_community_save);
        btn_community_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-진우-", "저장하기");
                commnty = new Commnty();
                commnty.setTitle(et_title.getText().toString());
                commnty.setContents(et_contents.getText().toString());
                //commnty.setTitle("제목입니다.");
                //commnty.setContents("내용입니다.");
                commnty.setMember_seq(member.getMember_seq());

                if(!checkCommnty(commnty)){
                    return;
                }
                Log.d("-진우-", commnty.toString());
                RegisterCommntyTask task = new RegisterCommntyTask(commnty);
                task.execute();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        //슬라이드메뉴에 있는 내 아이 목록
        updateScreenSlide();

        Log.d("-진우-", "CommunityWriteActivity 에 onResume() : " + member.toString());
    }

    //public void onClick(View v) {

//            case R.id.pic:
//                /*Intent gallery = new Intent();
//                gallery.setType("image/-");
//                gallery.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), SELECT_PICTURE);
//
//                @Override
//                protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//                if (resultCode == RESULT_OK) {
//                    if (requestCode == SELECT_PICTURE) {
//                    }
//                }*/
//
//                /*Intent gallery = new Intent(Intent.ACTION_PICK);
//                gallery.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
//                gallery.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(gallery, GET_PICTURE_URI);
//
//                @Override
//                protected void onActivityResult(int requestCode, int resultCode, Intent data){
//                    if (resultCode == GET_PICTURE_URI){
//                        if (requestCode == Activity.RESULT_OK) {
//                            URI uri = data.getData();
//                        }
//                    }
//                }*/
//                break;

   // }

    //Commnty의 내용체크
    private boolean checkCommnty(Commnty commnty){
        if(commnty.getTitle().length() <= 0 || commnty.getContents().length() <= 0){
            Toast.makeText(getApplicationContext(), "제목과 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private class RegisterCommntyTask extends AsyncTask<Void, Void, String> {

        private Commnty commnty;
        private ProgressDialog dialog = new ProgressDialog(CommunityWriteActivity.this);

        public RegisterCommntyTask(Commnty commnty) {
            this.commnty = commnty;
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
            Log.d("-진우-", Utility.commnty2json(commnty));
            String result = null;
            CommntyAPI service = ServiceGenerator.createService(CommntyAPI.class, "CommunityWriteActivity");
            Call<String> call = service.registerCommnty(commnty);
            try {
                result = call.execute().body();
            } catch (IOException e){
                Log.d("-진우-", "글 저장 실패");
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
}