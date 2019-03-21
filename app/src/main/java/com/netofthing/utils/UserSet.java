package com.netofthing.utils;

import android.text.TextUtils;

import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.netofthing.R;

/**
 * Created by 郭青枫 on 2018/1/12 0012.
 * 用户设置单例
 * <p>
 * 红涨绿跌
 * 夜间模式
 * 统一默认单位
 * 语言切换]
 * 开关自定义汇率
 * k线用户操作保存
 */

public class UserSet {


    private static class userSet {
        private static UserSet userSet = new UserSet();
    }

    public static UserSet getinstance() {
        return userSet.userSet;
    }

    public int getDropColor() {
        boolean redRise = isRedRise();
        int i = redRise ? R.color.decreasing_color : R.color.increasing_color;
        return i;
    }

    public int getRiseColor() {
        boolean redRise = isRedRise();
        int i = redRise ? R.color.increasing_color : R.color.decreasing_color;
        return i;
    }

    public int getbgDropColor() {
        boolean redRise = isRedRise();
        int i = redRise ? R.color.new_green : R.color.feature;
        return i;
    }

    public int getbgRiseColor() {
        boolean redRise = isRedRise();
        int i = redRise ? R.color.feature : R.color.new_green;
        return i;
    }

    public boolean isRedRise() {
        return SaveUtil.getInstance().getBoolean("isRedRise");
    }

    public void setRedRise(boolean isRedRise) {
        SaveUtil.getInstance().saveBoolean("isRedRise", isRedRise);
    }




    //用户设置k线缩放级别
    public float getKlineScale() {
        String KlineScale = SaveUtil.getInstance().getString("KlineScale");
        if (TextUtils.isEmpty(KlineScale)) {
            return 1f;
        } else {
            return Float.parseFloat(KlineScale);
        }
    }

    public void setKlineScale(float KlineScale) {
        SaveUtil.getInstance().saveString("KlineScale", KlineScale + "");
    }




    //usdt价格显示设置
    public static final String show_usd = "show_usd";
    public static final String show_usdt = "show_usdt";
    public static final String show_customize_usdt = "show_customize_usdt";

    public void setShowUsdtPrice(String type) {
        SaveUtil.getInstance().saveString("showUsdtPrice", type);
        BigUIUtil.getinstance().setShowUsdtPrice(type);
    }

    public String getShowUSdtPrice() {
        return SaveUtil.getInstance().getString("showUsdtPrice");
    }

    public void setShowCustomizePrice(String price) {
        SaveUtil.getInstance().saveString("ShowCustomizePrice", price);
    }

    public String getShowCustomizePrice() {
        return SaveUtil.getInstance().getString("ShowCustomizePrice");
    }

    public boolean isShowShortLine() {
        boolean isShowShortLine = SaveUtil.getInstance().getBoolean("isShowShortLine");
        //        if (!isFirst) {
        //            setNight(true);
        //        }
        return isShowShortLine;
    }

    public void setShowShortLine(boolean isFirst) {
        SaveUtil.getInstance().saveBoolean("isShowShortLine", isFirst);
    }

    public boolean isAppPush() {
        boolean isAppPush = SaveUtil.getInstance().getBoolean("isAppPush");
        return isAppPush;
    }

    public void setAppPush(boolean isAppPush) {
        SaveUtil.getInstance().saveBoolean("isAppPush", isAppPush);
    }

    public boolean isAppNightPush() {
        boolean isAppNightPush = SaveUtil.getInstance().getBoolean("isAppNightPush");
        return isAppNightPush;
    }

    public void setAppNightPush(boolean isAppNightPush) {
        SaveUtil.getInstance().saveBoolean("isAppNightPush", isAppNightPush);
    }


    public boolean isToastPushSetting() {
        boolean isToastPushSetting = SaveUtil.getInstance().getBoolean("isToastPushSetting");
        return isToastPushSetting;
    }

    public void setToastPushSetting(boolean isToastPushSetting) {
        SaveUtil.getInstance().saveBoolean("isToastPushSetting", isToastPushSetting);
    }
    public boolean isToastNeedPushSetting() {
        boolean isToastNeedPushSetting = SaveUtil.getInstance().getBoolean("isToastNeedPushSetting");
        return isToastNeedPushSetting;
    }

    public void setToastNeedPushSetting(boolean isToastNeedPushSetting) {
        SaveUtil.getInstance().saveBoolean("isToastNeedPushSetting", isToastNeedPushSetting);
    }
    public boolean isUseQuickSetup() {
        boolean isUseQuickSetup = SaveUtil.getInstance().getBoolean("isUseQuickSetup");
        return isUseQuickSetup;
    }

    public void setUseQuickSetup(boolean isUseQuickSetup) {
        SaveUtil.getInstance().saveBoolean("isUseQuickSetup", isUseQuickSetup);
    }

    public boolean isMarketNoList() {
        boolean isUseQuickSetup = SaveUtil.getInstance().getBoolean("isMarketNoList");
        return isUseQuickSetup;
    }

    public void setMarketNoList(boolean isMarketNoList) {
        SaveUtil.getInstance().saveBoolean("isMarketNoList", isMarketNoList);
    }
    public boolean isShowRateKline() {
        boolean isShowRateKline = SaveUtil.getInstance().getBoolean("isShowRateKline");
        return isShowRateKline;
    }

    public void setShowRateKline(boolean isShowRateKline) {
        SaveUtil.getInstance().saveBoolean("isShowRateKline", isShowRateKline);
    }

}
