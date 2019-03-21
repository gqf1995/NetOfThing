package com.netofthing.mvp.databinder;

import com.netofthing.mvp.delegate.KlineInfoDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class KlineInfoBinder extends BaseDataBind<KlineInfoDelegate> {

    public KlineInfoBinder(KlineInfoDelegate viewDelegate) {
        super(viewDelegate);
    }


}