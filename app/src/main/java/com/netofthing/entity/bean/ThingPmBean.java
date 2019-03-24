package com.netofthing.entity.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 郭青枫 on 2019/3/21.
 */

public class ThingPmBean extends ThingKlineBean {

    /**
     * createTime : 2019-03-23T16:14:37.722+0000
     * highScore : 99.0
     * lowScore : 79.0
     * startScore : 99.0
     * endScore : 99.0
     * avgScore : 87.94117647058823
     * totalScore : 14950.0
     * count : 170
     * realTimeScore : 79.0
     * timestamp : 0
     * history : [{"22:41":79},{"22:52":79},{"23:02":79},{"23:12":79},{"23:23":79},{"23:33":79},{"23:34":79},{"23:43":79},{"23:54":79},{"00:04":79},{"00:14":79}]
     * warningList : null
     * id : null
     * sparePartName : 二级齿轮
     * deviceName : null
     * equipmentId : null
     * sort : 2
     */


    private String totalScore;
    private int count;
    private String realTimeScore;
    private int timestamp;
    private String id;
    private String sparePartName;
    private String deviceName;
    private String equipmentId;
    private int sort;
    private List<Map<String, Float>> history;

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRealTimeScore() {
        return realTimeScore;
    }

    public void setRealTimeScore(String realTimeScore) {
        this.realTimeScore = realTimeScore;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getSparePartName() {
        return sparePartName;
    }

    public void setSparePartName(String sparePartName) {
        this.sparePartName = sparePartName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<Map<String, Float>> getHistory() {
        return history;
    }

    public void setHistory(List<Map<String, Float>> history) {
        this.history = history;
    }
}
