package com.netofthing.widget.chart;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.circledialog.res.drawable.RadiuBg;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.netofthing.R;

/**
 * Created by 郭青枫 on 2019/3/24.
 */

public class RealLineMarkerView extends MarkerView {

    private TextView tv_title;
    private FrameLayout fl_root;

    public RealLineMarkerView(Context context) {
        super(context, R.layout.layout_real_marker_view);//这个布局自己定义
        tv_title = (TextView) findViewById(R.id.tv_title);
        fl_root = (FrameLayout) findViewById(R.id.fl_root);
        tv_title.setBackground(new RadiuBg(
                CommonUtils.getColor(R.color.mark_color),
                5, 5, 5, 5
        ));
        tv_title.getBackground().mutate().setAlpha(150);
        tv_title.setTextColor(CommonUtils.getColor(R.color.white));
    }

    //显示的内容
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (tv_title != null) {
            tv_title.setText(e.getVal() +"\n" +
                    e.getData());
        }
    }

    @Override
    public int getXOffset(float xpos) {
        return -105;
    }

    @Override
    public int getYOffset(float ypos) {
        return -70;
    }

    //    //标记相对于折线图的偏移量
    //    @Override
    //    public MPPointF getOffset() {
    //        return new MPPointF(-(getWidth() / 2), -getHeight());
    //    }


}