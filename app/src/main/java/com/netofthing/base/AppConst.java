package com.netofthing.base;


/**
 * 全局配置
 */
public class AppConst {

    //http://47.98.56.206:1913/symbol/info?name=testCoin.

    public static final String app2BaseUrl = "https://blz.bicoin.com.cn";

    public static final boolean isLog = true;

    public static final String CACHE_EXCHANGENAME = "cache_exchangeName";//交易所 名称缓存
    public static final String CACHE_EXCHANGE_RATE = "cache_exchange_rate";//汇率缓存
    public static final String CACHE_KLINE = "cache_Kline";//
    public static final String CACHE_CHOOSE = "cache_choose";//用户自选 onlykey
    public static final String CACHE_SEARCH_HISTORY = "cache_search_history";//搜索历史
    public static final String CACHE_CUSTOM_RATE = "cache_custom_rate";//自定义汇率
    public static final String CACHE_COIN_INFO = "cache_coin_info";//币种资料页面
    public static final String CACHE_EXCH_COIN_LIST = "cache_exch_coin_list";//交易所列表
    public static final String CACHE_MARKET_COIN_LIST = "cache_market_coin_list";//市值列表
    public static final String rulesUrl = "http://rule.bicoin.com.cn";
    public static final String myRewardUrl = "http://rule.bicoin.com.cn/35/16/p501606933c9693";

    public static final String httpsCer = "blz.cer";
    public static final String umS1 = "Bradar";
    public static final String aliasS1 = "bradar";
    public static final String packageName = "com.netofthing";
    public static final String apkName = "币coin.apk";
    public static final boolean isChinese = true;
    public static final String webAddress = isChinese ? "/resource" : "/i18n";

    private static final String webUrl = "http://116.62.232.175:3002";
    public static final String feedbackUrl = webUrl + "/modules/serviceMessage.html#/feedback";
    public static final String communityUrl = webUrl + "/modules/serviceMessage.html#/community";
    public static final String currencyUrl = webUrl + "/modules/serviceMessage.html#/currency";
    public static final String regUrl = webUrl + "/modules/notice.html#/reg";
    public static final String aboutvipUrl = "http://app.bicoin.com.cn/web/aboutvip.html";
    public static final String userruleUrl = "http://app.bicoin.com.cn/web/userrule.html";
    public static final String aboutprivacyUrl = "http://app.bicoin.com.cn/web/aboutprivacy.html";
    public static final String aboutUsUrl = "http://app.bicoin.com.cn/web/aboutbicoin.html";


}
