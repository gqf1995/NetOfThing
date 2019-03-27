package com.netofthing.mvp.delegate;

import android.view.View;
import android.widget.LinearLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.netofthing.R;

public class ShowImgsDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_imgs;
    }


    public static class ViewHolder {
        public View rootView;
        public LinearLayout lin_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.lin_root = (LinearLayout) rootView.findViewById(R.id.lin_root);
        }

    }
}