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
public class RecordActivity extends SherlockFragment {

    TextView textTitleBar;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_record, container, false);

        textTitleBar = (TextView) getSherlockActivity().findViewById(R.id.textTitle);
        textTitleBar.setText("기록 조회");

        return v;
    }

}