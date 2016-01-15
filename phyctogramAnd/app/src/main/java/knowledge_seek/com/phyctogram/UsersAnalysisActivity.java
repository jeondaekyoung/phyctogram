
package knowledge_seek.com.phyctogram;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.phyctogram.domain.Height;
import knowledge_seek.com.phyctogram.domain.Users;
import knowledge_seek.com.phyctogram.kakao.common.BaseActivity;
import knowledge_seek.com.phyctogram.retrofitapi.ServiceGenerator;
import knowledge_seek.com.phyctogram.retrofitapi.UsersAPI;
import retrofit.Call;

public class UsersAnalysisActivity extends BaseActivity {

    //레이아웃 - 슬라이드 메뉴
    private ImageButton btn_left;
    private LinearLayout ic_screen;
    private CircularImageView img_profile;      //슬라이드 내 이미지
    private TextView tv_member_name;            //슬라이드 내 이름

    //리포트 공유 팝업
    private PopupWindow popup;
    private ImageButton btn_share;

    private LinearLayout ll_capture;                   //캡쳐화면
    private TextView tv_users_name;               //아이 이름 출력
    private ImageView iv_my_animal;                        //캐릭터
    private TextView tv_height;                         //최종신장
    private TextView tv_grow;                           //성장 값
    private TextView tv_rank;                           //상위
    private TextView tv_analysis_height50;          //분석
    private ImageView iv_analysis_height50_diff;
    private TextView tv_analysis_height50_diff;
    private TextView tv_analysis_height;
    private ImageView iv_analysis_height_diff;
    private TextView tv_analysis_height_diff;


    //데이터
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    private CombinedChart mChart;
    private final int itemcount = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-진우-", "UsersAnalysisActivity.onCreate() 실행");

        //setContentView(R.layout.include_analysis);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        //화면페이지
        ic_screen = (LinearLayout)findViewById(R.id.ic_screen);
        LayoutInflater.from(this).inflate(R.layout.include_analysis, ic_screen, true);
        //슬라이드메뉴 셋팅
        initSildeMenu();

        //슬라이드 내 이미지
        img_profile = (CircularImageView) findViewById(R.id.img_profile);
        //슬라이드 내 이름
        tv_member_name = (TextView) findViewById(R.id.tv_member_name);
        //슬라이드 내 아이 목록(ListView)에서 아이 선택시
        tv_users_name = (TextView) findViewById(R.id.tv_users_name);
        lv_usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nowUsers = (Users)usersListSlideAdapter.getItem(position);
                Log.d("-진우-", "선택한 아이 : " + nowUsers.toString());
                Toast.makeText(getApplicationContext(), "'" + nowUsers.getName() + "' 아이를 선택하였습니다", Toast.LENGTH_LONG).show();

                if (tv_users_name != null) {
                    tv_users_name.setText(nowUsers.getName());
                }

                //내 아이 메인(분석) 정보 계산하기
                FindUsersAnalysisInfoTask task = new FindUsersAnalysisInfoTask();
                task.execute();

            }
        });

        //레이아웃 정의
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLeftSlideAnimationToggle();
            }
        });

        ll_capture = (LinearLayout)findViewById(R.id.ll_capture);
        //리포트 공유 팝업
        btn_share = (ImageButton) findViewById(R.id.btn_share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_capture.buildDrawingCache();
                Bitmap captureView = ll_capture.getDrawingCache();
                //FileOutputStream fos;
                /*try {
                    fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/capture.jpeg");
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/

                View popupView = getLayoutInflater().inflate(R.layout.activity_report_share, null);
                //레이아웃 정의
                Button btn_close = (Button)popupView.findViewById(R.id.btn_close);
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });
                ImageView iv_capture= (ImageView)popupView.findViewById(R.id.iv_capture);
                iv_capture.setImageBitmap(captureView);
                popup = new PopupWindow(popupView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                popup.setAnimationStyle(-1); // 애니메이션 설정(-1:설정, 0:설정안함)
                popup.showAtLocation(popupView, Gravity.CENTER, 0, -100);
            }
        });

        mChart = (CombinedChart) findViewById(R.id.chart1);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        CombinedData data = new CombinedData(mMonths);

        data.setData(generateLineData());
        data.setData(generateBarData());
//      data.setData(generateBubbleData());
//      data.setData(generateScatterData());
//      data.setData(generateCandleData());

        mChart.setData(data);
        mChart.invalidate();

        //내 아이 메인(분석) 정보 등
        iv_my_animal = (ImageView)findViewById(R.id.iv_my_animal);
        tv_height = (TextView)findViewById(R.id.tv_height);
        tv_grow = (TextView)findViewById(R.id.tv_grow);
        tv_rank = (TextView)findViewById(R.id.tv_rank);
        tv_analysis_height50 = (TextView)findViewById(R.id.tv_analysis_height50);
        iv_analysis_height50_diff = (ImageView)findViewById(R.id.iv_analysis_height50_diff);
        tv_analysis_height50_diff = (TextView)findViewById(R.id.tv_analysis_height50_diff);
        tv_analysis_height = (TextView)findViewById(R.id.tv_analysis_height);
        iv_analysis_height_diff = (ImageView)findViewById(R.id.iv_analysis_height_diff);
        tv_analysis_height_diff = (TextView)findViewById(R.id.tv_analysis_height_diff);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-진우-", "UsersAnalysisActivity.onResume() 실행");

        //슬라이드메뉴 셋팅(내 아이 목록, 계정이미지, 내 아이 메인(분석)정보)
        UsersAnalysisTask task = new UsersAnalysisTask();
        task.execute(img_profile);

        Log.d("-진우-", "UsersAnalysisActivity 에 onResume() : " + member.toString());

        //슬라이드메뉴 계정이름 셋팅
        String name = null;
        if (member.getJoin_route().equals("kakao")) {
            name = member.getKakao_nickname() + " 님";
        } else if (member.getJoin_route().equals("facebook")) {
            name = member.getFacebook_name() + " 님";
        } else {
            name = member.getName() + " 님";
        }
        if (name != null) {
            tv_member_name.setText(name);
        }

        Log.d("-진우-", "UsersAnalysisActivity.onResume() 끝");
    }




    //<--그래프그리기
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

    //-> 그래프기리기
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

    //분석페이지 초기 데이터조회(슬라이드 내 아이 목록, 내 이미지)
    private class UsersAnalysisTask extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(UsersAnalysisActivity.this);
        private List<Users> usersTask;
        private CircularImageView img_profileTask;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            Bitmap mBitmap = null;
            img_profileTask = (CircularImageView) params[0];

            //슬라이드메뉴에 있는 내 아이 목록
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Users");
            Call<List<Users>> call = service.findUsersByMember(String.valueOf(member.getMember_seq()));
            try {
                usersTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 목록 가져오기 실패");
            }

            //이미지 불러오기
            String image_url = null;
            if (member.getJoin_route().equals("kakao")) {
                image_url = member.getKakao_thumbnailimagepath();
                InputStream in = null;
                try {
                    Log.d("-진우-", "이미지 주소 : " + image_url);
                    in = new URL(image_url).openStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (member.getJoin_route().equals("facebook")) {
                image_url = "http://graph.facebook.com/" + member.getFacebook_id() + "/picture?type=large";
                //이미지 불러오기
                InputStream in = null;
                try {
                    //페이스북은 jpg파일이 링크 걸린 것이 아니다.
                    //http://graph.facebook.com/userid/picture?type=large
                    Log.d("-진우-", "이미지 주소 : " + image_url);

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(image_url)
                            .build();
                    com.squareup.okhttp.Response response = client.newCall(request).execute();
                    in = response.body().byteStream();
                    mBitmap = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                Log.d("-진우-", "이미지읽어옴");
                img_profileTask.setImageBitmap(bitmap);
            }

            if (usersTask != null && usersTask.size() > 0) {
                Log.d("-진우-", "내 아이는 몇명? " + usersTask.size());
                for (Users u : usersTask) {
                    Log.d("-진우-", "내 아이 : " + u.toString());
                }
                usersList = usersTask;

                usersListSlideAdapter.setUsersList(usersList);
                if (nowUsers == null) {
                    nowUsers = usersTask.get(0);
                }
                Log.d("-진우-", "메인 유저는 " + nowUsers.toString());
                tv_users_name.setText(nowUsers.getName());
                //내 아이 메인(분석) 정보 계산하기
                FindUsersAnalysisInfoTask task = new FindUsersAnalysisInfoTask();
                task.execute();
            } else {
                Log.d("-진우-", "성공했으나 등록된 내아이가 없습니다");
            }

            int height = getListViewHeight(lv_usersList);
            lv_usersList.getLayoutParams().height = height;
            usersListSlideAdapter.notifyDataSetChanged();

            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }

    //내 아이 메인(분석) 정보 계산하기
    //
    private class FindUsersAnalysisInfoTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog dialog = new ProgressDialog(UsersAnalysisActivity.this);
        private List<Height> heightTask = new ArrayList<Height>();
        private List<Height> analysisTask = new ArrayList<Height>();

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //내 아이 메인(분석)정보 계산하기
            UsersAPI service = ServiceGenerator.createService(UsersAPI.class, "Height");
            Call<List<Height>> call = service.findUsersMainInfoByUserSeq(nowUsers.getUser_seq());
            try {
                heightTask = call.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 메인(분석) 정보 실패");
            }

            //내 아이 키성장 분석
            UsersAPI service1 = ServiceGenerator.createService(UsersAPI.class, "Height");
            Call<List<Height>> call1 = service.findUsersAnalysisByUserSeq(nowUsers.getUser_seq());
            try {
                analysisTask = call1.execute().body();
            } catch (IOException e) {
                Log.d("-진우-", "내 아이 키성장 분석 실패");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("-진우-", heightTask.size() + " 개 조회(메인분석)");
            /*for (Height h : heightTask) {
                Log.d("-진우-", "최근신장 : " + h.toString());
            }*/
            //성장키 계산
            for (int i = 0; i < heightTask.size()-1; i++) {
                heightTask.get(i).setGrow(String.format("%.1f", (heightTask.get(i).getHeight() - heightTask.get(i + 1).getHeight()) ));
            }

            if(heightTask.size() == 0){
                //기록이 없으면 끝
                iv_my_animal.setImageResource(R.drawable.sample);
                tv_height.setText("-");
                tv_grow.setText("-");
                tv_rank.setText("-");
                tv_analysis_height50.setText("-");
                tv_analysis_height50_diff.setText("-");
                tv_analysis_height.setText("-");
                tv_analysis_height_diff.setText("-");
                dialog.dismiss();
                super.onPostExecute(aVoid);
                return;
            }

            //내 아이 이미지
            String imgName = "@drawable/" + heightTask.get(0).getAnimal_img().substring(0,12);
            String packName = self.getPackageName();
            Log.d("-진우-", "확인 : " + imgName + ", " + packName);
            iv_my_animal.setImageResource(getResources().getIdentifier(imgName, "drawable", packName));
            //최종신장
            tv_height.setText(String.valueOf(heightTask.get(0).getHeight()));
            //성장키
            if(heightTask.size() == 2) {
                if (Double.valueOf(heightTask.get(0).getGrow()) >= 0) {
                    tv_grow.setText("+" + heightTask.get(0).getGrow());
                }
            }
            //상위
            tv_rank.setText(String.valueOf(heightTask.get(0).getRank()));

            //키 성장 분석 합니다....
            int analysisSize = analysisTask.size();
            if(analysisSize <= 0){
                //키성장 분석할 키 데이터가 없다
                tv_analysis_height50.setText("-");
                tv_analysis_height50_diff.setText("-");
                tv_analysis_height.setText("-");
                tv_analysis_height_diff.setText("-");
                dialog.dismiss();
                super.onPostExecute(aVoid);
                return;
            }


            Log.d("-진우-", analysisSize + " 개 조회(키성장분석)");
            for(Height h : analysisTask) {
                Log.d("-진우-", "키성장분석 : " + h.toString());
            }
            StringBuilder analysis_height50 = new StringBuilder();
            analysis_height50.append("평균 ").append(analysisTask.get(0).getHeight_50()).append(" cm");
            double analysis_height050_diff = Double.parseDouble(String.format("%.1f", analysisTask.get(0).getHeight() - analysisTask.get(0).getHeight_50()));
            //Log.d("-진우-", "분석 결과 : " + analysis_height50 + ", " + analysis_height050_diff);
            tv_analysis_height50.setText(analysis_height50);
            if(analysis_height050_diff > 0){
                iv_analysis_height50_diff.setImageResource(R.drawable.icon_report_up);
            } else {
                iv_analysis_height50_diff.setImageResource(R.drawable.icon_report_down);
            }
            tv_analysis_height50_diff.setText(String.valueOf(Math.abs(analysis_height050_diff)));

            //키 데이터가 1개일 경우
            if(analysisSize == 1){
                tv_analysis_height.setText("-");
                tv_analysis_height_diff.setText("-");
                dialog.dismiss();
                super.onPostExecute(aVoid);
                return;
            }

            //기간
            String dateFrom = analysisTask.get(analysisSize-1).getMesure_date();
            String dateTo = analysisTask.get(0).getMesure_date();
            //Log.d("-진우-", "기간1 : " + dateFrom + ", " + dateTo);
            int sYear = Integer.parseInt(dateFrom.substring(0,4));
            int sMonth = Integer.parseInt(dateFrom.substring(5, 7));
            int eYear = Integer.parseInt(dateTo.substring(0, 4));
            int eMonth = Integer.parseInt(dateTo.substring(5,7));
            Log.d("-진우-", "기간2 : " + sYear + "-" + sMonth+ ", " + eYear + "-" + eMonth);
            int month_diff = (eYear - sYear)* 12 + (eMonth - sMonth);
            double analysis_height50_diff =  Double.parseDouble(String.format("%.1f", analysisTask.get(0).getHeight_50() - analysisTask.get(analysisSize-1).getHeight_50()));
            double analysis_height_diff =  Double.parseDouble(String.format("%.1f", analysisTask.get(0).getHeight() - analysisTask.get(analysisSize-1).getHeight()));
            StringBuilder analysis_height = new StringBuilder();
            analysis_height.append(month_diff).append("개월동안 ").append(analysis_height50_diff).append("cm 성장");
            double diff = Double.parseDouble(String.format("%.1f", analysis_height_diff - analysis_height50_diff));
            Log.d("-진우-", "분석 결과2 : " + analysis_height + ", " + analysis_height_diff + ", " + analysis_height50_diff + " = "
                    + (analysis_height_diff - analysis_height50_diff) + ", " + diff);
            tv_analysis_height.setText(analysis_height);
            if(diff > 0){
                iv_analysis_height_diff.setImageResource(R.drawable.icon_report_up);
            } else {
                iv_analysis_height_diff.setImageResource(R.drawable.icon_report_down);
            }
            tv_analysis_height_diff.setText(String.valueOf(Math.abs(diff)));


            dialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }
}
