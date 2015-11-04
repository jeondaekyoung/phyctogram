package knowledge_seek.com.phyctogram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * Created by dkfka on 2015-11-04.
 */
public class UserAddActivity extends SherlockFragment {

    TextView textTitleBar;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_user_add, container, false);

        textTitleBar = (TextView) getSherlockActivity().findViewById(R.id.textTitle);
        textTitleBar.setText("사용자 추가");

        return v;
    }

}