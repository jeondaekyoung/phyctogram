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
public class QaViewActivity extends Activity {
    WebView webview;
    private int qa_seq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_qa_view);

        //데이터셋팅
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            qa_seq = (int)bundle.getSerializable("qa_seq");
            Log.d("-진우-", "공지사항 번호 : " + qa_seq);
        }


        //텍스트뷰 스크롤바 달기
        TextView tv_content = (TextView) findViewById(R.id.qa_content);
        tv_content.setMovementMethod(new ScrollingMovementMethod());


        webview = (WebView) findViewById(R.id.wb_qa);
        webview.setWebViewClient(new WebClient()); // 응룡프로그램에서 직접 url 처리
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webview.loadUrl("http://www.phyctogram.com/qa/view.do?qa_seq=" + qa_seq);
    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
