package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.view.View;

import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;

/**
 * Created by dkfka on 2015-11-26.
 */
public class CommunityWriteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commuinity_write);

    }

    public void onClick(View v) {

        switch (v.getId()) {
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