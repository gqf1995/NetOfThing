package com.netofthing.mvp.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;

import com.blankj.utilcode.util.TimeUtils;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.netofthing.R;
import com.netofthing.adapter.ThingPmAdapter;
import com.netofthing.entity.bean.ThingKlineBean;
import com.netofthing.entity.bean.ThingPmBean;
import com.netofthing.entity.bean.kline.DataParse;
import com.netofthing.entity.bean.kline.KLineBean;
import com.netofthing.mvp.databinder.KlineInfoBinder;
import com.netofthing.mvp.delegate.KlineInfoDelegate;
import com.netofthing.utils.DateUtils;
import com.netofthing.widget.chart.BlzKlineDraw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class KlineInfoActivity extends BaseDataBindActivity<KlineInfoDelegate, KlineInfoBinder> {

    String[] names = {"一级齿轮", "二级齿轮", "三级齿轮", "高速轴", "轴承"};
    String[] nums = {"98", "65", "72", "31", "89"};
    String[] colors = {"#f25657", "#f25657", "#72d746", "#6b6f82", "#72d746",};

    @Override
    protected Class<KlineInfoDelegate> getDelegateClass() {
        return KlineInfoDelegate.class;
    }

    @Override
    public KlineInfoBinder getDataBinder(KlineInfoDelegate viewDelegate) {
        return new KlineInfoBinder(viewDelegate);
    }

    List<ThingPmBean> pmDatas = new ArrayList<>();
    String json = "{\"code\":\"0\",\"msg\":\"SUCCESS\",\"data\":[{\"id\":\"415264fe62eb60585f12565c46574bbe\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":77.0,\"lowScore\":61.0,\"startScore\":79.0,\"endScore\":10.0,\"avgScore\":70.0,\"createTime\":\"2019-02-18T16:00:00.000+0000\"},{\"id\":\"4f11bcabe0ef256ffe5bbdb9a3c9796a\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":24.0,\"lowScore\":3.0,\"startScore\":77.0,\"endScore\":20.0,\"avgScore\":51.0,\"createTime\":\"2019-02-19T16:00:00.000+0000\"},{\"id\":\"4f604ad8037fe01577cf370585a451cd\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":76.0,\"lowScore\":73.0,\"startScore\":99.0,\"endScore\":10.0,\"avgScore\":18.0,\"createTime\":\"2019-02-20T16:00:00.000+0000\"},{\"id\":\"c6bce839e7d3d54936018f31c68c6cfd\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":60.0,\"lowScore\":52.0,\"startScore\":31.0,\"endScore\":59.0,\"avgScore\":72.0,\"createTime\":\"2019-02-21T16:00:00.000+0000\"},{\"id\":\"aee549449fe68b31be73d3be1967c43e\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":94.0,\"lowScore\":67.0,\"startScore\":11.0,\"endScore\":0.0,\"avgScore\":20.0,\"createTime\":\"2019-02-22T16:00:00.000+0000\"},{\"id\":\"f49b6d7c6b0a9b3e7b0295513a648fe5\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":49.0,\"lowScore\":40.0,\"startScore\":49.0,\"endScore\":68.0,\"avgScore\":86.0,\"createTime\":\"2019-02-23T16:00:00.000+0000\"},{\"id\":\"73311dc93e03d0bd2994e183b0609d40\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":85.0,\"lowScore\":32.0,\"startScore\":0.0,\"endScore\":71.0,\"avgScore\":42.0,\"createTime\":\"2019-02-24T16:00:00.000+0000\"},{\"id\":\"db38f58342a0885309f377ed2056da86\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":90.0,\"lowScore\":20.0,\"startScore\":97.0,\"endScore\":88.0,\"avgScore\":86.0,\"createTime\":\"2019-02-25T16:00:00.000+0000\"},{\"id\":\"655af954853361186ccdf188eb5c64e5\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":50.0,\"lowScore\":22.0,\"startScore\":77.0,\"endScore\":49.0,\"avgScore\":18.0,\"createTime\":\"2019-02-26T16:00:00.000+0000\"},{\"id\":\"5b2b7af7dca5539a0675a922dd69e057\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":54.0,\"lowScore\":44.0,\"startScore\":33.0,\"endScore\":97.0,\"avgScore\":21.0,\"createTime\":\"2019-02-27T16:00:00.000+0000\"},{\"id\":\"ead9755596bee720a407ceaa22d34ce6\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":34.0,\"lowScore\":19.0,\"startScore\":64.0,\"endScore\":75.0,\"avgScore\":28.0,\"createTime\":\"2019-02-28T16:00:00.000+0000\"},{\"id\":\"6b62a2e785da1db7fd3f168cedd99e8b\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":84.0,\"lowScore\":24.0,\"startScore\":35.0,\"endScore\":28.0,\"avgScore\":35.0,\"createTime\":\"2019-03-01T16:00:00.000+0000\"},{\"id\":\"b1d72716d5144402c75d8390cb14e2b1\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":99.0,\"lowScore\":0.0,\"startScore\":99.0,\"endScore\":99.0,\"avgScore\":85.74,\"createTime\":\"2019-03-03T10:04:18.000+0000\"},{\"id\":\"2d710e6827b5b289d643e62728069190\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":99.0,\"lowScore\":0.0,\"startScore\":99.0,\"endScore\":48.0,\"avgScore\":85.91,\"createTime\":\"2019-03-04T12:03:07.000+0000\"},{\"id\":\"e1c1b5310fffd2f1ac4c41b2cfa8e54f\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":99.0,\"lowScore\":59.0,\"startScore\":99.0,\"endScore\":99.0,\"avgScore\":92.96,\"createTime\":\"2019-03-12T10:00:01.000+0000\"},{\"id\":\"8faa7d263a0c3773fda576046bd817dc\",\"targetId\":\"59fd9d88a4dc41bc87d417da0d90e58b\",\"targetType\":1,\"targetRedisKey\":\"ASK_01_0011\",\"highScore\":99.0,\"lowScore\":99.0,\"startScore\":99.0,\"endScore\":99.0,\"avgScore\":98.35,\"createTime\":\"2019-03-13T10:00:36.000+0000\"},{\"id\":null,\"targetId\":null,\"targetType\":0,\"targetRedisKey\":null,\"highScore\":0.0,\"lowScore\":0.0,\"startScore\":37.0,\"endScore\":99.0,\"avgScore\":92.2155388471178,\"createTime\":\"2019-03-21T11:12:27.248+0000\"}]}";

    List<KLineBean> datas = new ArrayList<>();
    List<ThingKlineBean> thingDatas;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(""));
        for (int i = 0; i < colors.length; i++) {
            ThingPmBean thingPmBean = new ThingPmBean();
            thingPmBean.setColor(colors[i]);
            thingPmBean.setName(names[i]);
            thingPmBean.setNum(nums[i]);
            pmDatas.add(thingPmBean);
        }
        viewDelegate.viewHolder.tv_days.setText(Html.fromHtml(
                "预计离警戒线还有<font color=\"#d02c39\">" + "223" + "</font>天"
        ));


    }

    @Override
    protected void onResume() {
        super.onResume();
        Disposable subscribe = Observable.interval(0, 200, TimeUnit.MILLISECONDS)
                .take(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong != 0 && mData == null) {
                            thingDatas = GsonUtil.getInstance().toList(json, "data", ThingKlineBean.class);
                            for (int i = 0; i < thingDatas.size(); i++) {
                                KLineBean kLineBean = new KLineBean();
                                kLineBean.setClose(thingDatas.get(i).getEndScore());
                                kLineBean.setHigh(thingDatas.get(i).getHighScore());
                                kLineBean.setOpen(thingDatas.get(i).getStartScore());
                                kLineBean.setLow(thingDatas.get(i).getLowScore());
                                kLineBean.setVolume(new BigDecimal("0"));
                                Date time = DateUtils.getTime(thingDatas.get(i).getCreateTime());
                                kLineBean.setTimestamp(TimeUtils.date2Millis(time) / 1000);
                                datas.add(kLineBean);
                            }
                            initKline();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
        addRequest(subscribe);
    }

    DataParse mData;
    BlzKlineDraw klineDraw;

    private void initKline() {

        ThingPmAdapter adapter = new ThingPmAdapter(viewDelegate.getActivity(), pmDatas, viewDelegate.viewHolder.recycler_view.getMeasuredHeight());
        viewDelegate.viewHolder.recycler_view.setLayoutManager(new LinearLayoutManager(viewDelegate.getActivity()));
        viewDelegate.viewHolder.recycler_view.setAdapter(adapter);

        mData = new DataParse();
        klineDraw = new BlzKlineDraw();
        klineDraw.initView(this, viewDelegate.viewHolder.combinedchart, viewDelegate.viewHolder.v);
        mData.parseKLine(datas);
        klineDraw.initData(mData);
        viewDelegate.viewHolder.combinedchart.setOnMaxLeftLinsener(null);
        klineDraw.setData(mData);
        klineDraw.isFullScreen(false);
        klineDraw.setKlineShowType(1);
        initLine();
        handler.sendEmptyMessageDelayed(1, 1000 * 60);
    }

    int i = 0;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    List<KLineBean> lineBeans = new ArrayList<>();
                    lineBeans.add(mData.getKLineDatas().get(mData.getKLineDatas().size() - 1));
                    KLineBean kLineBean = new KLineBean();
                    kLineBean.setClose(mData.getKLineDatas().get(i).getClose());
                    kLineBean.setHigh(mData.getKLineDatas().get(i).getHigh());
                    kLineBean.setOpen(mData.getKLineDatas().get(i).getOpen());
                    kLineBean.setLow(mData.getKLineDatas().get(i).getLow());
                    kLineBean.setVolume(new BigDecimal("0"));
                    kLineBean.setTimestamp(mData.getKLineDatas().get(mData.getKLineDatas().size() - 1).getTimestamp() + 60);
                    lineBeans.add(kLineBean);
                    klineDraw.updata(lineBeans);
                    klineDraw.isFullScreen(false);
                    i++;
                    handler.sendEmptyMessageDelayed(1, 1000 * 60);
                    break;
            }
        }
    };

    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例

    private void initLine() {

        LineChart lineChart = viewDelegate.viewHolder.linChart;
        int lineColor = CommonUtils.getColor(R.color.color_font4);
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(false);
        lineChart.setScaleXEnabled(true);
        lineChart.setScaleYEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);
        lineChart.setDescription("");
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //lineChart.setMarkerView(new RealLineMarkerView(getActivity(), leftUnit, leftSize));
        lineChart.setExtraOffsets(5, 5, 5, 5);
        lineChart.highlightValues(null);
        lineChart.fitScreen();

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        leftYAxis.setEnabled(false);
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(true);
        rightYaxis.setDrawGridLines(false);
        rightYaxis.setDrawLabels(false);
        leftYAxis.setDrawGridLines(true);
        leftYAxis.setLabelCount(5, true);
        leftYAxis.setTextColor(CommonUtils.getColor(R.color.color_font2));
        leftYAxis.setGridColor(lineColor);
        rightYaxis.setGridColor(lineColor);

        leftYAxis.setAxisLineColor(lineColor);
        rightYaxis.setAxisLineColor(lineColor);
        xAxis.setAxisLineColor(lineColor);
        xAxis.setGridColor(lineColor);

        xAxis.setTextColor(CommonUtils.getColor(R.color.color_font2));


        legend = lineChart.getLegend();
        legend.setEnabled(false);


        List<Entry> entries = new ArrayList<>();
        List<String> xVals = new ArrayList<>();

        for (int i = 0; i < thingDatas.size(); i++) {
            Entry entry = new Entry(thingDatas.get(i).getAvgScore().floatValue(), i);
            String sTime = thingDatas.get(i).getCreateTime();
            entry.setData(sTime);
            entries.add(entry);
            xVals.add(sTime);
        }

        //if (lineChart.getData() == null) {
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setColor(CommonUtils.getColor(R.color.increasing_color));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(true);
        lineDataSet.setLineWidth(1.5f);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setHighLightColor(CommonUtils.getColor(R.color.mark_color));
        lineDataSet.setHighlightEnabled(true);


        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(CommonUtils.getColor(R.color.decreasing_color));
        //设置折线图填充
        lineDataSet.setDrawFilled(false);
        LineData lineData = new LineData(xVals, lineDataSet);

        lineChart.setData(lineData);

        lineChart.postInvalidateDelayed(0);
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {

        }
    }

}
