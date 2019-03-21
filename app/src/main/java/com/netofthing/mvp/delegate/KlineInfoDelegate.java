package com.netofthing.mvp.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.netofthing.R;
import com.netofthing.widget.chart.KCombinedChart;
import com.netofthing.widget.chart.TpLineChart;

public class KlineInfoDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kline_info;
    }


    public static class ViewHolder {
        public View rootView;

        public TextView tv_title;
        public TextView tv_type;
        public TextView tv_days;
        public TextView tv_week;
        public TextView tv_month;
        public TextView tv_ji;
        public TextView tv_year;
        public KCombinedChart combinedchart;
        public KCombinedChart v;
        public TpLineChart linChart;
        public TextView tv_name;
        public ImageView iv_piv;
        public TextView tv_num;
        public TextView tv_num1;
        public TextView tv_num2;
        public ImageView iv_type;
        public RecyclerView recycler_view;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_type = (TextView) rootView.findViewById(R.id.tv_type);
            this.tv_days = (TextView) rootView.findViewById(R.id.tv_days);
            this.tv_week = (TextView) rootView.findViewById(R.id.tv_week);
            this.tv_month = (TextView) rootView.findViewById(R.id.tv_month);
            this.tv_ji = (TextView) rootView.findViewById(R.id.tv_ji);
            this.tv_year = (TextView) rootView.findViewById(R.id.tv_year);
            this.combinedchart = (KCombinedChart) rootView.findViewById(R.id.combinedchart);
            this.v = (KCombinedChart) rootView.findViewById(R.id.v);
            this.linChart = (TpLineChart) rootView.findViewById(R.id.linChart);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.iv_piv = (ImageView) rootView.findViewById(R.id.iv_piv);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
            this.tv_num1 = (TextView) rootView.findViewById(R.id.tv_num1);
            this.tv_num2 = (TextView) rootView.findViewById(R.id.tv_num2);
            this.iv_type = (ImageView) rootView.findViewById(R.id.iv_type);
            this.recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        }

    }
}