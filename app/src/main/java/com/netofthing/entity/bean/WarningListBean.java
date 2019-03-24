package com.netofthing.entity.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 郭青枫 on 2019/3/24.
 */

public class WarningListBean extends ThingKlineBean {
    /**
     * createTime : null
     * highScore : 0.0
     * lowScore : 0.0
     * startScore : 0.0
     * endScore : 0.0
     * avgScore : 0.0
     * totalScore : 0.0
     * count : 0
     * realTimeScore : 0.0 //分数
     * timestamp : 0
     * history : []
     * warningList : null
     * deviceName : ASK_01_0013
     * equipmentName : 立磨减速机
     * sort : 1
     * firstLevelGear : null
     * secondLevelGear : null
     * thirdLevelGear : null
     * highSpeedBearing : null
     * bearing : null
     * electric : null
     * temperature : 21.6
     * rssi : 25.0
     * battery : 3.78
     * temperatureHistory : [{"20:12":23.4},{"20:42":23.1},{"21:12":22.7},{"21:42":22.5},{"22:12":22.2},{"22:42":22},{"23:12":21.8},{"23:42":21.6},{"23:42":21.6}]
     * conkOutList : []
     */

    private int count;
    private String realTimeScore;
    private int timestamp;
    private String deviceName;
    private String equipmentName;
    private int sort;
    private String temperature;
    private String rssi;
    private String battery;
    private List<Map<String, Float>> temperatureHistory;

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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public List<Map<String, Float>> getTemperatureHistory() {
        return temperatureHistory;
    }

    public void setTemperatureHistory(List<Map<String, Float>> temperatureHistory) {
        this.temperatureHistory = temperatureHistory;
    }
}
