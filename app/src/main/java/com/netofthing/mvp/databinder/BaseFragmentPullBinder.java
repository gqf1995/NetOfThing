package com.netofthing.mvp.databinder;


import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.netofthing.mvp.delegate.BaseFragentPullDelegate;

/**
 * Created by 郭青枫 on 2017/9/27.
 * 统一的 fragment列表接口 代理
 */

public class BaseFragmentPullBinder extends BaseDataBind<BaseFragentPullDelegate> {
    public BaseFragmentPullBinder(BaseFragentPullDelegate viewDelegate) {
        super(viewDelegate);
    }


//    public Disposable deal_currency(
//            RequestCallback requestCallback) {
//        getBaseMapWithUid();
//        return new HttpRequest.Builder()
//                .setRequestCode(0x124)
//                .setDialog(viewDelegate.getNetConnectDialog())
//                .setRequestUrl(HttpUrl.getIntance().deal_currency)
//                .setShowDialog(false)
//                .setRequestName("获取币种")
//                .setRequestMode(HttpRequest.RequestMode.GET)
//                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
//                .setRequestObj(baseMap)
//                .setRequestCallback(requestCallback)
//                .build()
//                .RxSendRequest();
//    }

}