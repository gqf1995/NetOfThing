package com.netofthing.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.netofthing.mvp.delegate.BaseActivityPullDelegate;
import com.netofthing.server.HttpUrl;

import io.reactivex.disposables.Disposable;


/**
 * Created by 郭青枫 on 2017/9/27.
 * 统一的 activity列表页面 接口代理
 */

public class BaseActivityPullBinder<T extends BaseActivityPullDelegate> extends BaseDataBind<T> {
    public BaseActivityPullBinder(T viewDelegate) {
        super(viewDelegate);
    }

    public Disposable pro_line_list(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x999)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestUrl(HttpUrl.getIntance().pro_line_list)
                .setShowDialog(false)
                .setRequestName("生产线实时数据接口")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable real_time(
            String proLineId,
            RequestCallback requestCallback) {
        put("proLineId", proLineId);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestUrl(HttpUrl.getIntance().real_time)
                .setShowDialog(false)
                .setRequestName("生产线列表")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable real_time(
            String proLineId,
            int code,
            RequestCallback requestCallback) {
        put("proLineId", proLineId);
        put("isAll", false);
        return new HttpRequest.Builder()
                .setRequestCode(code)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestUrl(HttpUrl.getIntance().real_time)
                .setShowDialog(false)
                .setRequestName("生产线列表")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable k_data(
            String proLineId,
            int code,
            RequestCallback requestCallback) {
        put("proLineId", proLineId);
        put("period", 7);
        return new HttpRequest.Builder()
                .setRequestCode(code)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestUrl(HttpUrl.getIntance().k_data)
                .setShowDialog(false)
                .setRequestName("生产线k线数据接口")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
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
}