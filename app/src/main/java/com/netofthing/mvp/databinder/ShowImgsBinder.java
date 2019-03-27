package com.netofthing.mvp.databinder;

import com.netofthing.mvp.delegate.ShowImgsDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class ShowImgsBinder extends BaseDataBind<ShowImgsDelegate> {

    public ShowImgsBinder(ShowImgsDelegate viewDelegate) {
        super(viewDelegate);
    }


}