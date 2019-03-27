package com.netofthing.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.fivefivelike.mybaselibrary.base.BaseAdapter;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.netofthing.R;
import com.netofthing.entity.bean.ThingBean;
import com.netofthing.entity.bean.ThingKlineBean;
import com.netofthing.entity.bean.ThingLineBean;
import com.netofthing.entity.bean.kline.DataParse;
import com.netofthing.entity.bean.kline.KLineBean;
import com.netofthing.utils.DateUtils;
import com.netofthing.utils.UserSet;
import com.netofthing.widget.chart.RealLineMarkerView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 郭青枫 on 2018/7/16 0016.
 */

public class ProductionLineAdapter extends BaseAdapter<ThingBean> {

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("MM-dd");
    int getRiseColor;
    int getDropColor;
    int border_color;
    int color_font2;
    int color_e5e5e5;
    int color_999999;
    int color_font4;
    int ma1;
    int ma5;
    int ma7;
    int ma10;
    int ma20;
    int ma30;
    private TextView tv_name;
    private TextView tv_num;
    private LinearLayout lin_root;

    int hight = 0;
    private TextView tv_tem;
    private ImageView iv_type;
    private TextView tv_rate;
    private RecyclerView recycler_view;
    private TextView tv_no;
    private LineChart lineChart;
    private CombinedChart combinedChart;

    public void setHight(int hight) {
        this.hight = hight;
    }

    public ProductionLineAdapter(Context context, List<ThingBean> datas) {
        super(context, R.layout.adapter_production_line, datas);
        getRiseColor = CommonUtils.getColor(UserSet.getinstance().getRiseColor());
        getDropColor = CommonUtils.getColor(UserSet.getinstance().getDropColor());
        border_color = CommonUtils.getColor(R.color.color_f5f5f5);
        color_font2 = CommonUtils.getColor(R.color.color_font2);
        color_e5e5e5 = CommonUtils.getColor(R.color.color_e5e5e5);
        color_999999 = CommonUtils.getColor(R.color.color_999999);


        color_font4 = CommonUtils.getColor(R.color.color_font3);
        ma1 = CommonUtils.getColor(R.color.mark_color);
        ma5 = CommonUtils.getColor(R.color.ma5);
        ma7 = CommonUtils.getColor(R.color.ma7);
        ma10 = CommonUtils.getColor(R.color.ma10);
        ma20 = CommonUtils.getColor(R.color.ma20);
        ma30 = CommonUtils.getColor(R.color.ma30);
    }

    @Override
    protected void convert(ViewHolder holder, ThingBean s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_num = holder.getView(R.id.tv_num);
        lin_root = holder.getView(R.id.lin_root);
        tv_tem = holder.getView(R.id.tv_tem);
        iv_type = holder.getView(R.id.iv_type);
        tv_rate = holder.getView(R.id.tv_rate);
        recycler_view = holder.getView(R.id.recycler_view);
        tv_no = holder.getView(R.id.tv_no);
        lineChart = holder.getView(R.id.lineChart);
        combinedChart = holder.getView(R.id.combinedChart);
        ViewGroup.LayoutParams layoutParams = lin_root.getLayoutParams();
        layoutParams.height = hight;
        lin_root.setLayoutParams(layoutParams);


        tv_name.setText(s.getProLineName());


        if (s.getData() != null) {
            lineChart.setVisibility(View.VISIBLE);
            combinedChart.setVisibility(View.VISIBLE);

            tv_tem.setText(s.getData().getRealTimeScore());
            tv_num.setText(s.getData().getStartScore());
            String s1 = "0";
            if (new BigDecimal(s.getData().getStartScore()).doubleValue() != 0) {
                s1 = new BigDecimal(s.getData().getRealTimeScore())
                        .subtract(new BigDecimal(s.getData().getStartScore()))
                        .multiply(new BigDecimal("100"))
                        .divide(new BigDecimal(s.getData().getStartScore()), 2, RoundingMode.DOWN)
                        .toPlainString();
            }
            tv_rate.setText(
                    s1 + "%"
            );
            tv_rate.setTextColor(CommonUtils.getColor(new BigDecimal(s1).doubleValue() >= 0 ? UserSet.getinstance().getRiseColor() :
                    UserSet.getinstance().getDropColor()));
            tv_tem.setTextColor(tv_rate.getTextColors());

            iv_type.setImageDrawable(CommonUtils.getDrawable(new BigDecimal(s1).floatValue() >= 0 ?
                    R.drawable.upload : R.drawable.down));


            initLine(lineChart, s.getData());
            initKline(combinedChart, s.getkData());

            if (ListUtils.isEmpty(s.getData().getWarningList())) {
                recycler_view.setVisibility(View.GONE);
                tv_no.setVisibility(View.VISIBLE);
            } else {
                recycler_view.setVisibility(View.VISIBLE);
                tv_no.setVisibility(View.GONE);
                if (recycler_view.getAdapter() == null) {
                    ProductionPmAdapter productionPmAdapter = new ProductionPmAdapter(mContext, s.getData().getWarningList());
                    recycler_view.setLayoutManager(new LinearLayoutManager(mContext) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    recycler_view.setAdapter(productionPmAdapter);
                } else {
                    ((ProductionPmAdapter) recycler_view.getAdapter()).setData(s.getData().getWarningList());
                }
            }
        } else {
            lineChart.setVisibility(View.GONE);
            combinedChart.setVisibility(View.GONE);
            recycler_view.setVisibility(View.GONE);
            tv_no.setVisibility(View.VISIBLE);
        }

    }


    private void initKline(CombinedChart mChartKline, List<ThingKlineBean> s) {

        //X轴标签的类
        XAxis xAxisKline;
        //Y轴左侧的线
        YAxis axisLeftKline;
        //Y轴右侧的线
        YAxis axisRightKline;

        mChartKline.setScaleEnabled(true);//启用图表缩放事件
        mChartKline.setDrawBorders(true);//是否绘制边线
        mChartKline.setBorderWidth(1);//边线宽度，单位dp
        mChartKline.setDragEnabled(true);//启用图表拖拽事件
        mChartKline.setScaleYEnabled(false);//启用Y轴上的缩放
        mChartKline.setBorderColor(border_color);//边线颜色
        mChartKline.setDescription("");//右下角对图表的描述信息
        mChartKline.setMinOffset(0f);
        mChartKline.setExtraOffsets(5, 5, 5, 5);


        Legend lineChartLegend = mChartKline.getLegend();
        lineChartLegend.setEnabled(false);//是否绘制 Legend 图例
        lineChartLegend.setForm(Legend.LegendForm.CIRCLE);


        //bar x y轴
        xAxisKline = mChartKline.getXAxis();
        xAxisKline.setEnabled(true);
        xAxisKline.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        xAxisKline.setDrawGridLines(false);//是否显示X坐标轴上的刻度竖线，默认是true
        xAxisKline.setDrawAxisLine(true); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxisKline.setGridColor(border_color);
        xAxisKline.setAxisLineColor(border_color);
        xAxisKline.setTextSize(9f);
        //xAxisKline.enableGridDashedLine(10f, 10f, 0f);//虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        xAxisKline.setTextColor(color_font4);//设置字的颜色
        xAxisKline.setPosition(XAxis.XAxisPosition.BOTTOM);//设置值显示在什么位置
        xAxisKline.setAvoidFirstLastClipping(true);//设置首尾的值是否自动调整，避免被遮挡
        xAxisKline.setLabelsToSkip(1);


        axisLeftKline = mChartKline.getAxisLeft();
        axisLeftKline.setEnabled(true);
        axisLeftKline.setDrawGridLines(false);
        axisLeftKline.setDrawAxisLine(true);
        axisLeftKline.setDrawZeroLine(true);
        axisLeftKline.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisLeftKline.setZeroLineColor(border_color);
        axisLeftKline.setGridColor(border_color);
        axisLeftKline.setAxisLineColor(border_color);
        axisLeftKline.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                return new BigDecimal(value+"").setScale(0,RoundingMode.DOWN).toPlainString()+"℃";
            }
        });
        axisLeftKline.setDrawLabels(true);
        //axisLeftKline.enableGridDashedLine(10f, 10f, 0f);
        axisLeftKline.setTextColor(color_font4);
        axisLeftKline.setTextSize(9.4f);
        //        axisLeftKline.setGridColor(CommonUtils.getColor(R.color.minute_grayLine));
        axisLeftKline.setLabelCount(5, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布


        axisRightKline = mChartKline.getAxisRight();
        axisRightKline.setDrawLabels(false);
        axisRightKline.setDrawGridLines(true);
        axisRightKline.setDrawZeroLine(true);
        axisRightKline.setGridColor(border_color);
        axisRightKline.setZeroLineColor(border_color);
        axisRightKline.setAxisLineColor(border_color);
        axisRightKline.setDrawAxisLine(true);
        axisRightKline.setLabelCount(5, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        axisRightKline.setDrawTopYLabelEntry(false);

        mChartKline.setDragDecelerationEnabled(true);
        mChartKline.setDragDecelerationFrictionCoef(0.9f);


        DataParse mData = new DataParse();
        List<KLineBean> datas = new ArrayList<>();
        for (int i = 0; i < s.size(); i++) {
            KLineBean kLineBean = new KLineBean();
            kLineBean.setClose(s.get(i).getEndScore());
            kLineBean.setHigh(s.get(i).getHighScore());
            kLineBean.setOpen(s.get(i).getStartScore());
            kLineBean.setLow(s.get(i).getLowScore());
            kLineBean.setVolume(new BigDecimal("0"));
            Date time = DateUtils.getTime(s.get(i).getCreateTime());
            kLineBean.setTimestamp(TimeUtils.date2Millis(time) / 1000);
            datas.add(kLineBean);
        }
        mData.parseKLine(datas);
        mData.initLineDatas(datas);

        CandleDataSet set = new CandleDataSet(mData.getCandleEntries(), "");
        set.setDrawHorizontalHighlightIndicator(false);
        set.setHighlightEnabled(true);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setShadowWidth(1f);
        set.setValueTextSize(10f);
        set.setDecreasingColor(getDropColor);//设置开盘价高于收盘价的颜色
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setIncreasingColor(getRiseColor);//设置开盘价地狱收盘价的颜色
        set.setIncreasingPaintStyle(Paint.Style.FILL);
        set.setNeutralColor(getDropColor);//设置开盘价等于收盘价的颜色
        set.setShadowColorSameAsCandle(true);
        set.setHighlightLineWidth(0.5f);
        set.setHighLightColor(color_999999);
        set.setDrawValues(false);
        set.setValueTextColor(color_font2);
        set.setVisible(true);

        List<String> times = new ArrayList<>();
        times.addAll(mData.getXVals());
        CandleData candleData = new CandleData(times, set);

        CombinedData combinedData = new CombinedData(times);
        combinedData.setData(candleData);

        mChartKline.setData(combinedData);


        mChartKline.setDrawHighlightArrow(true);
        mChartKline.notifyDataSetChanged();
        mChartKline.postInvalidateDelayed(0);
        mChartKline.setScaleEnabled(true);
        mChartKline.setScaleYEnabled(false);
    }


    private void initLine(LineChart lineChart, ThingLineBean s) {

        XAxis xAxis;                //X轴
        YAxis leftYAxis;            //左侧Y轴
        YAxis rightYaxis;           //右侧Y轴
        Legend legend;              //图例

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
        lineChart.setMarkerView(new RealLineMarkerView(mContext));
        //设置XY轴动画效果
        lineChart.animateY(0);
        lineChart.animateX(0);
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

        for (int i = 0; i < s.getHistory().size(); i++) {
            for (String key : s.getHistory().get(i).keySet()) {
                Entry entry = new Entry(s.getHistory().get(i).get(key), entries.size());
                String sTime = key;
                entry.setData(sTime);
                entries.add(entry);
                xVals.add(sTime);
            }
        }


        //if (lineChart.getData() == null) {
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setColor(CommonUtils.getColor(R.color.increasing_color));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
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
}