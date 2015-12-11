
package knowledge_seek.com.phyctogram;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

import charting.charts.CombinedChart;
import charting.charts.CombinedChart.DrawOrder;
import charting.components.XAxis;
import charting.components.XAxis.XAxisPosition;
import charting.components.YAxis;
import charting.data.BarData;
import charting.data.BarDataSet;
import charting.data.BarEntry;
import charting.data.BubbleData;
import charting.data.BubbleDataSet;
import charting.data.BubbleEntry;
import charting.data.CandleData;
import charting.data.CandleDataSet;
import charting.data.CandleEntry;
import charting.data.CombinedData;
import charting.data.Entry;
import charting.data.LineData;
import charting.data.LineDataSet;
import charting.data.ScatterData;
import charting.data.ScatterDataSet;
import charting.utils.ColorTemplate;

public class CombinedChartActivity extends DemoBase {

    private CombinedChart mChart;
    private final int itemcount = 12;
    PopupWindow popup;
    View popupView;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.include_report);

        //리포트 공유 팝업
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, dm);
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, dm);

        linear = (LinearLayout) findViewById(R.id.linear);
        popupView = View.inflate(this, R.layout.activity_report_share, null);


        mChart = (CombinedChart) findViewById(R.id.chart1);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        // draw bars behind lines
        mChart.setDrawOrder(new DrawOrder[]{
                DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.CANDLE, DrawOrder.LINE, DrawOrder.SCATTER
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTH_SIDED);

        CombinedData data = new CombinedData(mMonths);

        data.setData(generateLineData());
        data.setData(generateBarData());
//      data.setData(generateBubbleData());
//      data.setData(generateScatterData());
//      data.setData(generateCandleData());

        mChart.setData(data);
        mChart.invalidate();
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(getRandom(15, 10), index));

        LineDataSet set = new LineDataSet(entries, "내 아이 성장곡선");
        set.setColor(Color.rgb(151, 118, 197));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(151, 118, 197));
        set.setCircleSize(5f);
        set.setFillColor(Color.rgb(151, 118, 197));
        set.setDrawCubic(true);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(0, 0, 0));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData() {

        BarData d = new BarData();

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new BarEntry(getRandom(15, 30), index));

        BarDataSet set = new BarDataSet(entries, "평균 성장 그래프");
        set.setColor(Color.rgb(220, 220, 220));
        set.setValueTextColor(Color.rgb(220, 220, 220));
        set.setValueTextSize(10f);
        d.addDataSet(set);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return d;
    }

    protected ScatterData generateScatterData() {

        ScatterData d = new ScatterData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(getRandom(20, 15), index));

        ScatterDataSet set = new ScatterDataSet(entries, "Scatter DataSet");
        set.setColor(Color.GREEN);
        set.setScatterShapeSize(7.5f);
        set.setDrawValues(false);
        set.setValueTextSize(10f);
        d.addDataSet(set);

        return d;
    }

    protected CandleData generateCandleData() {

        CandleData d = new CandleData();

        ArrayList<CandleEntry> entries = new ArrayList<CandleEntry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new CandleEntry(index, 20f, 10f, 13f, 17f));

        CandleDataSet set = new CandleDataSet(entries, "Candle DataSet");
        set.setColor(Color.rgb(80, 80, 80));
        set.setBodySpace(0.3f);
        set.setValueTextSize(10f);
        set.setDrawValues(false);
        d.addDataSet(set);

        return d;
    }

    protected BubbleData generateBubbleData() {

        BubbleData bd = new BubbleData();

        ArrayList<BubbleEntry> entries = new ArrayList<BubbleEntry>();

        for (int index = 0; index < itemcount; index++) {
            float rnd = getRandom(20, 30);
            entries.add(new BubbleEntry(index, rnd, rnd));
        }

        BubbleDataSet set = new BubbleDataSet(entries, "Bubble DataSet");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.WHITE);
        set.setHighlightCircleWidth(1.5f);
        set.setDrawValues(true);
        bd.addDataSet(set);

        return bd;
    }

    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }


    //리포트 공유 팝업 띄우기
    /*final Button btnshare = (Button) findViewById(R.id.btn_share);
    btnshare.setOnClickListener(new Button.OnClickListener(){
        popup.showAtLocation(linear, Gravity.CENTER, 0, 0); //중앙에 띄운다.

        //닫기버튼 클릭 이벤트
        Button btnClose = (Button) popupView.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }*/


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.combined, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionToggleLineValues: {
                for (DataSet<?> set : mChart.getData().getDataSets()) {
                    if (set instanceof LineDataSet)
                        set.setDrawValues(!set.isDrawValuesEnabled());
                }

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleBarValues: {
                for (DataSet<?> set : mChart.getData().getDataSets()) {
                    if (set instanceof BarDataSet)
                        set.setDrawValues(!set.isDrawValuesEnabled());
                }

                mChart.invalidate();
                break;
            }
        }
        return true;
    }*/
}
