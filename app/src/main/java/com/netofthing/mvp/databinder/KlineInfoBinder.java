package com.netofthing.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.netofthing.mvp.delegate.KlineInfoDelegate;
import com.netofthing.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class KlineInfoBinder extends BaseDataBind<KlineInfoDelegate> {

    public KlineInfoBinder(KlineInfoDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable equipment_real_time(
            String deviceName,
            RequestCallback requestCallback) {
        put("deviceName", deviceName);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestUrl(HttpUrl.getIntance().equipment_real_time)
                .setShowDialog(false)
                .setRequestName("生产线列表")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable equipment_k_data(
            int period,
            String deviceName,
            int code,
            RequestCallback requestCallback) {
        put("deviceName", deviceName);
        put("period", period);
        return new HttpRequest.Builder()
                .setRequestCode(code)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestUrl(HttpUrl.getIntance().equipment_k_data)
                .setShowDialog(false)
                .setRequestName("生产线列表")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}