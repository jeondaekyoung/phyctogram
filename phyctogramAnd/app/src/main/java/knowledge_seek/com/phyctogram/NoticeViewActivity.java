package knowledge_seek.com.phyctogram;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dkfka on 2016-02-11.
 */
public class NoticeViewActivity extends Activity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_notice_view);

        webview = (WebView) findViewById(R.id.wb_notice);
        webview.setWebViewClient(new WebClient()); // 응룡프로그램에서 직접 url 처리
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webview.loadUrl("http://www.naver.com");
    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
