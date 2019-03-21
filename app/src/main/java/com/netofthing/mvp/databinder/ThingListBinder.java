package com.netofthing.mvp.databinder;

import com.netofthing.mvp.delegate.ThingListDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class ThingListBinder extends BaseDataBind<ThingListDelegate> {

    public ThingListBinder(ThingListDelegate viewDelegate) {
        super(viewDelegate);
    }


}