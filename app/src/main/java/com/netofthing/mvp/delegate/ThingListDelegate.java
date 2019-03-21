package com.netofthing.mvp.delegate;

import com.netofthing.R;
import com.fivefivelike.mybaselibrary.base.BaseDelegate;

import android.view.View;

public class ThingListDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thing_list;
    }


    public static class ViewHolder {
        public View rootView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

        }

    }
}