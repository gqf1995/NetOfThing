package com.netofthing.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseAdapter;
import com.netofthing.R;
import com.netofthing.entity.bean.ThingPmBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/7/16 0016.
 */

public class ThingPmAdapter extends BaseAdapter<ThingPmBean> {


    private TextView tv_name;
    private TextView tv_num;
    private LinearLayout lin_root;

    int hight = 0;

    public ThingPmAdapter(Context context, List<ThingPmBean> datas, int hight) {
        super(context, R.layout.adapter_thing_pm, datas);
        this.hight = hight / datas.size();
    }


    @Override
    protected void convert(ViewHolder holder, ThingPmBean s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_num = holder.getView(R.id.tv_num);
        lin_root = holder.getView(R.id.lin_root);

        tv_name.setText(s.getSparePartName());
        tv_num.setText(s.getRealTimeScore());
        //tv_num.setTextColor(Color.parseColor(s.getColor()));


        ViewGroup.LayoutParams layoutParams = lin_root.getLayoutParams();
        layoutParams.height = hight;
        lin_root.setLayoutParams(layoutParams);

    }
}