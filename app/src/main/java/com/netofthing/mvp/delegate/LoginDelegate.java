package com.netofthing.mvp.delegate;

import android.view.View;
import android.widget.LinearLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.netofthing.R;

public class LoginDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    public static class ViewHolder {
        public View rootView;
        public RoundButton tv_login;
        public LinearLayout lin_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_login = (RoundButton) rootView.findViewById(R.id.tv_login);
            this.lin_root = (LinearLayout) rootView.findViewById(R.id.lin_root);
        }

    }
}