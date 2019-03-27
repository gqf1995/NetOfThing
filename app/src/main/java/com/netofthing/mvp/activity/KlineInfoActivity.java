package com.netofthing.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;

import com.blankj.utilcode.util.TimeUtils;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.glide.GlideUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.netofthing.R;
import com.netofthing.adapter.ThingPmAdapter;
import com.netofthing.entity.bean.ThingKlineBean;
import com.netofthing.entity.bean.ThingLineBean;
import com.netofthing.entity.bean.ThingPmBean;
import com.netofthing.entity.bean.kline.DataParse;
import com.netofthing.entity.bean.kline.KLineBean;
import com.netofthing.mvp.databinder.KlineInfoBinder;
import com.netofthing.mvp.delegate.KlineInfoDelegate;
import com.netofthing.utils.DateUtils;
import com.netofthing.utils.UserSet;
import com.netofthing.widget.chart.BlzKlineDraw;
import com.netofthing.widget.chart.RealLineMarkerView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KlineInfoActivity extends BaseDataBindActivity<KlineInfoDelegate, KlineInfoBinder> {


    @Override
    protected Class<KlineInfoDelegate> getDelegateClass() {
        return KlineInfoDelegate.class;
    }

    public static void startAct(Activity activity,
                                String id,
                                String type,
                                String name) {
        Intent intent = new Intent(activity, KlineInfoActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("name", name);
        activity.startActivity(intent);
    }

    private String id;
    private String type;
    private String name;

    int selectType = 7;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        name = intent.getStringExtra("name");
        addRequest(binder.equipment_real_time(type, this));
        addRequest(binder.equipment_k_data(selectType, type, 0x124, this));
        viewDelegate.viewHolder.tv_title.setText(name + " - ");
        viewDelegate.viewHolder.tv_name.setText(name);
        viewDelegate.viewHolder.tv_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType = 7;
                handler.removeCallbacksAndMessages(null);
                addRequest(binder.equipment_k_data(selectType, type, 0x124, KlineInfoActivity.this));
                viewDelegate.viewHolder.tv_type.setText("周数据");
            }
        });
        viewDelegate.viewHolder.tv_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType = 30;
                handler.removeCallbacksAndMessages(null);
                addRequest(binder.equipment_k_data(selectType, type, 0x124, KlineInfoActivity.this));
                viewDelegate.viewHolder.tv_type.setText("月数据");
            }
        });
        viewDelegate.viewHolder.tv_ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType = 90;
                handler.removeCallbacksAndMessages(null);
                addRequest(binder.equipment_k_data(selectType, type, 0x124, KlineInfoActivity.this));
                viewDelegate.viewHolder.tv_type.setText("季数据");
            }
        });
        viewDelegate.viewHolder.tv_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType = 365;
                handler.removeCallbacksAndMessages(null);
                addRequest(binder.equipment_k_data(selectType, type, 0x124, KlineInfoActivity.this));
                viewDelegate.viewHolder.tv_type.setText("年数据");
            }
        });

    }


    @Override
    public KlineInfoBinder getDataBinder(KlineInfoDelegate viewDelegate) {
        return new KlineInfoBinder(viewDelegate);
    }

    List<KLineBean> datas = new ArrayList<>();
    List<ThingKlineBean> thingDatas;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(""));
        getIntentData();
        viewDelegate.viewHolder.tv_days.setText(Html.fromHtml(
                "预计离警戒线还有<font color=\"#d02c39\">" + "223" + "</font>天"
        ));
    }


    DataParse mData;
    BlzKlineDraw klineDraw;

    private void initKline() {
        mData = new DataParse();
        klineDraw = new BlzKlineDraw();
        klineDraw.initView(this, viewDelegate.viewHolder.combinedchart, viewDelegate.viewHolder.v);
        mData.parseKLine(datas);
        klineDraw.initData(mData);
        viewDelegate.viewHolder.combinedchart.setOnMaxLeftLinsener(null);
        klineDraw.setData(mData);
        klineDraw.isFullScreen(false);
        klineDraw.setKlineShowType(1);
        handler.sendEmptyMessageDelayed(1, 1000 * 60);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    addRequest(binder.equipment_k_data(selectType, type, 0x125, KlineInfoActivity.this));
                    break;
            }
        }
    };

    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("MM-dd");

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
        lineChart.setMarkerView(new RealLineMarkerView(this));
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
        leftYAxis.setEnabled(true);
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(true);
        rightYaxis.setDrawGridLines(false);
        rightYaxis.setDrawLabels(false);
        leftYAxis.setDrawGridLines(true);
        leftYAxis.setDrawLabels(true);
        leftYAxis.setLabelCount(3, true);
        leftYAxis.setTextColor(CommonUtils.getColor(R.color.color_font2));
        leftYAxis.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                return new BigDecimal(value+"").setScale(0,RoundingMode.DOWN).toPlainString()+"℃";
            }
        });
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

        for (int i = 0; i < thingLineBean.getTemperatureHistory().size(); i++) {
            for (String key : thingLineBean.getTemperatureHistory().get(i).keySet()) {
                Entry entry = new Entry(thingLineBean.getTemperatureHistory().get(i).get(key), entries.size());
                String sTime = key;
                entry.setData(sTime);
                entries.add(entry);
                xVals.add(sTime);
            }
        }
        //        for (int i = 0; i < thingDatas.size(); i++) {
        //            Entry entry = new Entry(thingDatas.get(i).getAvgScore().floatValue(), i);
        //
        //
        //            Date time = DateUtils.getTime(thingDatas.get(i).getCreateTime());
        //            String sTime = TimeUtils.millis2String(TimeUtils.date2Millis(time) / 1000, DEFAULT_FORMAT);
        //            entry.setData(sTime);
        //            entries.add(entry);
        //            xVals.add(sTime);
        //        }

        //if (lineChart.getData() == null) {
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setColor(CommonUtils.getColor(R.color.increasing_color));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new BigDecimal(value + "").setScale(1, RoundingMode.DOWN).toPlainString();
            }
        });
        lineDataSet.setLineWidth(1.5f);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setHighLightColor(CommonUtils.getColor(R.color.mark_color));
        lineDataSet.setHighlightEnabled(true);


        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(7f);
        lineDataSet.setValueTextColor(CommonUtils.getColor(R.color.decreasing_color));
        //设置折线图填充
        lineDataSet.setDrawFilled(false);
        LineData lineData = new LineData(xVals, lineDataSet);

        lineChart.setData(lineData);

        lineChart.postInvalidateDelayed(0);
    }

    List<ThingPmBean> warningList;
    ThingLineBean thingLineBean;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                thingLineBean = GsonUtil.getInstance().toObj(data, ThingLineBean.class);
                warningList = GsonUtil.getInstance().toList(data, "warningList", ThingPmBean.class);

                GlideUtils.loadImage(
                        "http://47.101.57.230/img/" +
                                thingLineBean.getSort() + ".jpg", viewDelegate.viewHolder.iv_piv, GlideUtils.getNoCacheRO());

                String realTimeScore = GsonUtil.getInstance().getValue(data, "realTimeScore");
                String startScore = GsonUtil.getInstance().getValue(data, "startScore");
                viewDelegate.viewHolder.tv_num.setText(realTimeScore);
                viewDelegate.viewHolder.tv_num1.setText(startScore);
                String s1 = "0";
                if (new BigDecimal(startScore).doubleValue() != 0) {
                    s1 = new BigDecimal(realTimeScore)
                            .subtract(new BigDecimal(startScore))
                            .multiply(new BigDecimal("100"))
                            .divide(new BigDecimal(startScore), 2, RoundingMode.DOWN)
                            .toPlainString();
                }
                viewDelegate.viewHolder.tv_num2.setText(s1 + "%");
                viewDelegate.viewHolder.tv_num2.setTextColor(CommonUtils.getColor(new BigDecimal(s1).doubleValue() >= 0 ? UserSet.getinstance().getRiseColor() :
                        UserSet.getinstance().getDropColor()));
                viewDelegate.viewHolder.tv_num.setTextColor(viewDelegate.viewHolder.tv_num2.getTextColors());
                viewDelegate.viewHolder.iv_type.setImageDrawable(CommonUtils.getDrawable(new BigDecimal(s1).floatValue() >= 0 ?
                        R.drawable.upload : R.drawable.down));


                ThingPmAdapter adapter = new ThingPmAdapter(viewDelegate.getActivity(),
                        warningList,
                        viewDelegate.viewHolder.recycler_view.getMeasuredHeight());
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        ComponentesListActivity.startAct(viewDelegate.getActivity(),
                                type,
                                name
                        );
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
                viewDelegate.viewHolder.recycler_view.setLayoutManager(new LinearLayoutManager(viewDelegate.getActivity()));
                viewDelegate.viewHolder.recycler_view.setAdapter(adapter);
                if (!ListUtils.isEmpty(thingLineBean.getTemperatureHistory())) {
                    initLine();
                }

                break;
            case 0x124:
                thingDatas = GsonUtil.getInstance().toList(data, ThingKlineBean.class);
                datas.clear();
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
                if (!ListUtils.isEmpty(datas)) {
                    initKline();
                }
                break;
            case 0x125:
                List<ThingKlineBean> thingKlineBeans = GsonUtil.getInstance().toList(data, ThingKlineBean.class);
                List<KLineBean> datas = new ArrayList<>();
                for (int i = 0; i < thingKlineBeans.size(); i++) {
                    KLineBean kLineBean = new KLineBean();
                    kLineBean.setClose(thingKlineBeans.get(i).getEndScore());
                    kLineBean.setHigh(thingKlineBeans.get(i).getHighScore());
                    kLineBean.setOpen(thingKlineBeans.get(i).getStartScore());
                    kLineBean.setLow(thingKlineBeans.get(i).getLowScore());
                    kLineBean.setVolume(new BigDecimal("0"));
                    Date time = DateUtils.getTime(thingKlineBeans.get(i).getCreateTime());
                    long l = TimeUtils.date2Millis(time) / 1000;
                    kLineBean.setTimestamp(l);
                    if (mData.getKLineDatas().get(mData.getKLineDatas().size() - 1).getTimestamp() <= l) {
                        datas.add(kLineBean);
                    }
                }
                klineDraw.updata(datas);
                klineDraw.isFullScreen(false);
                handler.sendEmptyMessageDelayed(1, 1000 * 60);
                break;
        }
    }

}
