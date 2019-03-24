package com.netofthing.entity.bean;

import java.util.List;

/**
 * Created by 郭青枫 on 2019/3/24.
 */

public class ThingBean {
    /**
     * id : 0632f59f06384434b1a1589520edd89e
     * proLineName : 一号生产线
     * createTime : 2019-02-21T06:00:00.000+0000
     * sort : 1
     */

    private String id;
    private String proLineName;
    private String createTime;
    private int sort;

    private ThingLineBean data;
    private List<ThingKlineBean> kData;

    public ThingLineBean getData() {
        return data;
    }

    public void setData(ThingLineBean data) {
        this.data = data;
    }

    public List<ThingKlineBean> getkData() {
        return kData;
    }

    public void setkData(List<ThingKlineBean> kData) {
        this.kData = kData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProLineName() {
        return proLineName;
    }

    public void setProLineName(String proLineName) {
        this.proLineName = proLineName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
