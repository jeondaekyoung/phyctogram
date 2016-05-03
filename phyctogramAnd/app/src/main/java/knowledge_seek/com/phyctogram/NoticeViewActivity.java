package knowledge_seek.com.phyctogram;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by dkfka on 2016-02-11.
 */
public class NoticeViewActivity extends Activity {
    WebView webview;
    private int notice_seq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_notice_view);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            notice_seq = (int)bundle.getSerializable("notice_seq");
            Log.d("-진우-", "공지사항 번호 : " + notice_seq);
        }

        //텍스트뷰 스크롤바 달기
        TextView tv_content = (TextView) findViewById(R.id.notice_content);
        tv_content.setMovementMethod(new ScrollingMovementMethod());

        webview = (WebView) findViewById(R.id.wb_notice);
        webview.setWebViewClient(new WebClient()); // 응룡프로그램에서 직접 url 처리
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webview.loadUrl("http://www.phyctogram.com/notice/view.do?notice_seq=" + notice_seq);
    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
