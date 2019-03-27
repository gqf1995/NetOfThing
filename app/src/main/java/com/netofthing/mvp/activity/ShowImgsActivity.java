package com.netofthing.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.netofthing.mvp.databinder.ShowImgsBinder;
import com.netofthing.mvp.delegate.ShowImgsDelegate;

public class ShowImgsActivity extends BaseDataBindActivity<ShowImgsDelegate, ShowImgsBinder> {

    @Override
    protected Class<ShowImgsDelegate> getDelegateClass() {
        return ShowImgsDelegate.class;
    }

    @Override
    public ShowImgsBinder getDataBinder(ShowImgsDelegate viewDelegate) {
        return new ShowImgsBinder(viewDelegate);
    }

    public static void startAct(Activity activity,
                                String id,
                                String type,
                                String name) {
        Intent intent = new Intent(activity, ShowImgsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("name", name);
        activity.startActivity(intent);
    }

    private String id;
    private String type;
    private String name;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        name = intent.getStringExtra("name");
        viewDelegate.viewHolder.lin_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KlineInfoActivity.startAct(viewDelegate.getActivity(),
                        id, type, name);
            }
        });
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
