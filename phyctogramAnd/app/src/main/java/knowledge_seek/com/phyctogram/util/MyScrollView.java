package knowledge_seek.com.phyctogram.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 스크롤뷰 이벤트 처리
 * Created by sjw on 2016-02-02.
 */
public class MyScrollView extends ScrollView {
    int startX, endX;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("-진우-", "ScrollView에 이벤트 발생");
        final int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                Log.d("-진우-", "ACTION_MOVE 이벤트 발생");
                break;
            case MotionEvent.ACTION_DOWN:
                startX = (int)ev.getX();
                Log.d("-진우-", "ACTION_DOWN 이벤트 발생");
                break;
            case MotionEvent.ACTION_UP:
                endX = (int)ev.getX();
                Log.d("-진우-", "ACTION_UP 이벤트 발생");
                break;
            default: break;
        }
        Log.d("-진우-", "시작 : " + startX + ", 끝 : " + endX);
        if(startX <= 50) {
            Log.d("-진우-", "시작지점");
        } else {
            Log.d("-진우-", "시작지점으로 옳지 않다");
        }

        // return true; 의 경우 이벤트가 한번만 발생하고, 자식이벤트 발생안함
        return true;
        //return false;  의 경우 이벤트가 지속적으로 발생, 자식이벤트 발생
        //return super.onInterceptTouchEvent(ev);
    }
}
