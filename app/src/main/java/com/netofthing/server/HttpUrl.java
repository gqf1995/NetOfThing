package com.netofthing.server;

import com.netofthing.base.AppConst;

/**
 * Created by 郭青枫 on 2019/1/12 0012.
 */

public class HttpUrl {
    static HttpUrl httpUrl = new HttpUrl();

    public static HttpUrl getIntance() {
        if (httpUrl == null) {
            httpUrl = new HttpUrl();
        }
        return httpUrl;
    }

    /**
     * 生产线列表
     */
    public String pro_line_list = AppConst.app2BaseUrl  + "/pro_line/list";
    /**
     * 生产线实时数据接口
     */
    public String real_time = AppConst.app2BaseUrl  + "/pro_line/real_time";
    /**
     * 生产线k线数据接口
     */
    public String k_data = AppConst.app2BaseUrl  + "/pro_line/k_data";
    /**
     * 生产线实时数据接口
     */
    public String equipment_real_time = AppConst.app2BaseUrl  + "/equipment/real_time";
    /**
     * 生产线k线数据接口
     */
    public String equipment_k_data = AppConst.app2BaseUrl  + "/equipment/k_data";

}
