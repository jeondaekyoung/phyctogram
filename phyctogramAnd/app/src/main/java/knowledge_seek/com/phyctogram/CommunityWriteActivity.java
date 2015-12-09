package knowledge_seek.com.phyctogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-26.
 */
public class CommunityWriteActivity extends BaseActivity implements View.OnClickListener {

    /* slide menu */
//public static DisplayMetrics metrics;
//public static LinearLayout ll_mainLayout;
//public static LinearLayout ll_menuLayout;
//public static FrameLayout.LayoutParams leftMenuLayoutPrams;
//public static int leftMenuWidth;
//public static boolean isLeftExpanded;
    public static Button bt_left;
    public static Button btn1;
    public static Button btn2;
    public static Button btn3;
    public static Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commuinity_write);


        bt_left = (Button) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        initSildeMenu();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_left:
                menuLeftSlideAnimationToggle();
                break;
            case R.id.btn1:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                break;
            case R.id.btn2:

                break;
            case R.id.btn3:

                break;
            case R.id.btn4:

                break;
            case R.id.pic:
                /*Intent gallery = new Intent();
                gallery.setType("image/-");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), SELECT_PICTURE);

                @Override
                protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                    if (requestCode == SELECT_PICTURE) {
                    }
                }*/

                /*Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                gallery.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GET_PICTURE_URI);

                @Override
                protected void onActivityResult(int requestCode, int resultCode, Intent data){
                    if (resultCode == GET_PICTURE_URI){
                        if (requestCode == Activity.RESULT_OK) {
                            URI uri = data.getData();
                        }
                    }
                }*/
                break;

            case R.id.mov:

                break;


        }

    }
}