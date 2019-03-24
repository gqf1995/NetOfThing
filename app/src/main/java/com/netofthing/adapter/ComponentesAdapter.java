package com.netofthing.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseAdapter;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.netofthing.R;
import com.netofthing.entity.bean.ThingPmBean;
import com.netofthing.utils.UserSet;
import com.netofthing.widget.chart.RealLineMarkerView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2018/7/16 0016.
 */

public class ComponentesAdapter extends BaseAdapter<ThingPmBean> {

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

    int hight = 0;
    private TextView tv_name;
    private TextView tv_tem;
    private ImageView iv_type;
    private TextView tv_num;
    private TextView tv_rate;
    private LineChart lineChart;
    private LinearLayout lin_use;
    private LinearLayout lin_root;

    public void setHight(int hight) {
        this.hight = hight;
    }

    public ComponentesAdapter(Context context, List<ThingPmBean> datas) {
        super(context, R.layout.adapter_components, datas);
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
    protected void convert(ViewHolder holder, ThingPmBean s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_num = holder.getView(R.id.tv_num);
        lin_root = holder.getView(R.id.lin_root);
        tv_tem = holder.getView(R.id.tv_tem);
        iv_type = holder.getView(R.id.iv_type);
        tv_rate = holder.getView(R.id.tv_rate);
        lineChart = holder.getView(R.id.lineChart);


        tv_tem.setText(s.getRealTimeScore());
        tv_num.setText(s.getStartScore().toPlainString());
        String s1="0";
        if (s.getStartScore().doubleValue() != 0) {
            s1 = new BigDecimal(s.getRealTimeScore())
                    .subtract(s.getStartScore())
                    .divide(s.getStartScore(), 2, RoundingMode.DOWN)
                    .toPlainString();
        }

        tv_rate.setText(
                s1 + "%"
        );
        tv_rate.setTextColor(CommonUtils.getColor(new BigDecimal(s1).doubleValue() >= 0 ? UserSet.getinstance().getRiseColor() :
                UserSet.getinstance().getDropColor()));

        iv_type.setRotation(new BigDecimal(s1).floatValue() > 0 ? 0 : 180);

        tv_name.setText(s.getSparePartName());

        ViewGroup.LayoutParams layoutParams = lin_root.getLayoutParams();
        layoutParams.height = hight;
        lin_root.setLayoutParams(layoutParams);
        initLine(lineChart, s);
    }

    private void initLine(LineChart lineChart, ThingPmBean s) {

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

        for(int i=0;i< s.getHistory().size();i++){
            for (String key : s.getHistory().get(0).keySet()) {
                Entry entry = new Entry(s.getHistory().get(0).get(key), entries.size());
                String sTime=key;
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