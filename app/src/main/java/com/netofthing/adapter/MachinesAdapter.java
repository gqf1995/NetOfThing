package com.netofthing.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseAdapter;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.glide.GlideUtils;
import com.netofthing.R;
import com.netofthing.entity.bean.WarningListBean;
import com.netofthing.utils.UserSet;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by 郭青枫 on 2018/7/16 0016.
 */

public class MachinesAdapter extends BaseAdapter<WarningListBean> {


    int hight = 0;
    private TextView tv_name;
    private TextView tv_tem;
    private ImageView iv_type;
    private TextView tv_num;
    private TextView tv_rate;
    private ImageView iv_piv;
    private LinearLayout lin_use;
    private LinearLayout lin_root;
    private TextView tv_temperature;
    private TextView tv_battery;
    private TextView tv_rssi;


    public void setHight(int hight) {
        this.hight = hight;
    }

    public MachinesAdapter(Context context, List<WarningListBean> datas) {
        super(context, R.layout.adapter_machine, datas);
    }


    @Override
    protected void convert(ViewHolder holder, WarningListBean s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_num = holder.getView(R.id.tv_num);
        lin_root = holder.getView(R.id.lin_root);
        tv_tem = holder.getView(R.id.tv_tem);
        iv_type = holder.getView(R.id.iv_type);
        tv_rate = holder.getView(R.id.tv_rate);
        iv_piv = holder.getView(R.id.iv_piv);
        lin_use = holder.getView(R.id.lin_use);
        tv_temperature = holder.getView(R.id.tv_temperature);
        tv_battery = holder.getView(R.id.tv_battery);
        tv_rssi = holder.getView(R.id.tv_rssi);

        tv_name.setText(s.getEquipmentName());

        tv_tem.setText(s.getRealTimeScore());
        tv_num.setText(s.getStartScore().toPlainString());

        String s1 = "0";
        if (s.getStartScore().doubleValue() != 0) {
            s1 = new BigDecimal(s.getRealTimeScore())
                    .subtract(s.getStartScore())
                    .multiply(new BigDecimal("100"))
                    .divide(s.getStartScore(), 2, RoundingMode.DOWN)
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


        GlideUtils.loadImage(
                "http://47.101.57.230/img/"+
                s.getSort()+".jpg", iv_piv, GlideUtils.getNoCacheRO());

        tv_temperature.setText(s.getTemperature() + "℃");
        tv_battery.setText(s.getBattery() + "");
        tv_rssi.setText(s.getRssi() + "");

        ViewGroup.LayoutParams layoutParams = lin_root.getLayoutParams();
        layoutParams.height = hight;
        lin_root.setLayoutParams(layoutParams);

    }
}