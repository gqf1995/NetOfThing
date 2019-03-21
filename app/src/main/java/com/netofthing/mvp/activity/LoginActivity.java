package com.netofthing.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.netofthing.mvp.databinder.LoginBinder;
import com.netofthing.mvp.delegate.LoginDelegate;

public class LoginActivity extends BaseDataBindActivity<LoginDelegate, LoginBinder> {

    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    @Override
    public LoginBinder getDataBinder(LoginDelegate viewDelegate) {
        return new LoginBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewDelegate.viewHolder.lin_root.getBackground().mutate().setAlpha(150);
        viewDelegate.viewHolder.tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewDelegate.getActivity(), KlineInfoActivity.class));
                finish();
            }
        });

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
