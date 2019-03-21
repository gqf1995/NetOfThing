package com.netofthing.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.fivefivelike.mybaselibrary.base.BaseApp;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.utils.UUIDS;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.netofthing.mvp.activity.MainActivity;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

import java.util.Locale;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;


/**
 * Created by 郭青枫 on 2017/9/25.
 */

public class Application extends BaseApp {

    boolean isInitUm = false;
    public static boolean isInitPush = false;
    boolean isInitNoHttp = false;

    @Override
    public void onCreate() {
        super.onCreate();
        //融云初始化
        if (isMainProcess()) {
            initClient();
        }
    }


    @Override
    public void returnHeader(Headers headers) {
        if (headers.containsKey("userTempId")) {

        }
    }



    private void initClient() {
        //客户端进程中初始化操作
        if (isMainProcess()) {
            //initNohttp();
            //初始化数据库

            initNohttp();
            //开启log日志
            KLog.init(AppConst.isLog);
            //英文切换
            Configuration configuration = getResources().getConfiguration();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (!AppConst.isChinese) {
                configuration.locale = Locale.ENGLISH;
            }
            configuration.fontScale = 1;
            //更新配置
            getResources().updateConfiguration(configuration, displayMetrics);

            //初始化融云
            //initRongCloud();
            //初始化换肤
            initSkin();
        }
    }

    public native String key();

    public String getHttpKey() {
        return key();
    }
    private void initSkin() {
        SkinCompatManager.withoutActivity(GlobleContext.getInstance().getApplicationContext())                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(true)                  // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();
    }
    private void initNohttp() {

        InitializationConfig config = InitializationConfig.newBuilder(this)
                // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                .addHeader("mobilId", UUIDS.getUUID())
                .addHeader("from", "Android")
                .addHeader("appVersion", AppUtils.getAppVersionName())
                .addHeader("mobilKey", EncryptUtils.encryptMD5ToString(
                        UUIDS.getUUID(), UUIDS.getUUID()))
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        // 如果不使用缓存，setEnable(false)禁用。
                        new DBCacheStore(this).setEnable(false)
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。
                .cookieStore(
                        // 如果不维护cookie，setEnable(false)禁用。
                        new DBCookieStore(this).setEnable(false)
                )
                // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .networkExecutor(new OkHttpNetworkExecutor())
                .sslSocketFactory(null)
                .retry(1) // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();
        Logger.setDebug(false);
        Logger.setTag("NoHttpSample");// 打印Log的tag。
        NoHttp.initialize(config);
        isInitNoHttp = true;
    }




    //客户服务
    public void startCustomerService(Activity activity) {
        //        if (SingSettingDBUtil.getUserLogin() != null) {
        //            CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        //            CSCustomServiceInfo csInfo = csBuilder.nickName(SingSettingDBUtil.getUserLogin().getNickName()).build();
        //            RongIM.getInstance().startCustomerServiceChat(activity, "KEFU151728371459995", "客服中心", csInfo);
        //        } else {
        //            ToastUtil.show(CommonUtils.getString(R.string.str_toast_need_login));
        //        }
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行

        super.onTerminate();
    }

    public Class getLoginActivityClass() {
        return null;//LoginRegisteredActivity.class;
    }

    @Override
    public Class getMainActivityClass() {
        return MainActivity.class;
    }



    public class CrashHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            try {
                e.printStackTrace();
                //传入这个方法的参数e就是引起应用crash的异常，我们可以在这里获取异常信息，可以把异常信息上传到服务器以便统一分析，也可以保存在文件系统中
            } catch (Exception e1) {

            }
        }
    }
}
