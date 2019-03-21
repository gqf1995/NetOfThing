package com.netofthing.mvp.activity;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.netofthing.mvp.databinder.ThingListBinder;
import com.netofthing.mvp.delegate.ThingListDelegate;

public class ThingListActivity extends BaseDataBindActivity<ThingListDelegate, ThingListBinder> {

    @Override
    protected Class<ThingListDelegate> getDelegateClass() {
        return ThingListDelegate.class;
    }

    @Override
    public ThingListBinder getDataBinder(ThingListDelegate viewDelegate) {
        return new ThingListBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(""));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
