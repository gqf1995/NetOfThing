package com.netofthing.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseAdapter;
import com.netofthing.R;
import com.netofthing.entity.bean.WarningListBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/7/16 0016.
 */

public class ProductionPmAdapter extends BaseAdapter<WarningListBean> {


    private TextView tv_name;
    private TextView tv_num;
    private LinearLayout lin_root;


    public ProductionPmAdapter(Context context, List<WarningListBean> datas) {
        super(context, R.layout.adapter_production_pm, datas);
    }


    @Override
    protected void convert(ViewHolder holder, WarningListBean s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_num = holder.getView(R.id.tv_num);
        lin_root = holder.getView(R.id.lin_root);

        tv_name.setText(s.getEquipmentName());
        tv_num.setText(s.getRealTimeScore());
        //tv_num.setTextColor(Color.parseColor(s.getColor()));


    }
}