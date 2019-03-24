package com.netofthing.entity.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 郭青枫 on 2019/3/24.
 */

public class ThingLineBean {
    /**
     * createTime : 2019-03-23T16:04:19.335+0000
     * highScore : 73.0
     * lowScore : 37.0
     * startScore : 37.0
     * endScore : 64.8
     * avgScore : 55.734437704153926
     * totalScore : 119438.90000000186
     * count : 2143
     * realTimeScore : 64.8
     * timestamp : 0
     * history : [{"23:08":64.8},{"23:12":64.8},{"23:12":64.8},{"23:16":64.8},{"23:23":64.8},{"23:33":64.8},{"23:34":64.8},{"23:38":64.8},{"23:42":64.8},{"23:42":64.8},{"23:43":64.8},{"23:46":64.8},{"23:54":64.8},{"00:04":64.8}]
     * warningList : [{"createTime":null,"highScore":0,"lowScore":0,"startScore":0,"endScore":0,"avgScore":0,"totalScore":0,"count":0,"realTimeScore":0,"timestamp":0,"history":[],"warningList":null,"deviceName":"ASK_01_0013","equipmentName":"立磨减速机","sort":1,"firstLevelGear":null,"secondLevelGear":null,"thirdLevelGear":null,"highSpeedBearing":null,"bearing":null,"electric":null,"temperature":21.6,"rssi":25,"battery":3.78,"temperatureHistory":[{"20:12":23.4},{"20:42":23.1},{"21:12":22.7},{"21:42":22.5},{"22:12":22.2},{"22:42":22},{"23:12":21.8},{"23:42":21.6},{"23:42":21.6}],"conkOutList":[]},{"createTime":null,"highScore":0,"lowScore":0,"startScore":0,"endScore":0,"avgScore":0,"totalScore":0,"count":0,"realTimeScore":0,"timestamp":0,"history":[],"warningList":null,"deviceName":"ASK_01_0014","equipmentName":"窑主减速机","sort":2,"firstLevelGear":null,"secondLevelGear":null,"thirdLevelGear":null,"highSpeedBearing":null,"bearing":null,"electric":null,"temperature":22,"rssi":32,"battery":3.41,"temperatureHistory":[{"22:41":22.5},{"22:52":22.6},{"23:02":22.4},{"23:12":22.3},{"23:23":22.3},{"23:33":22.5},{"23:34":22.5},{"23:43":22.1},{"23:54":22.1},{"00:04":22}],"conkOutList":[]},{"createTime":null,"highScore":0,"lowScore":0,"startScore":0,"endScore":0,"avgScore":0,"totalScore":0,"count":0,"realTimeScore":0,"timestamp":0,"history":[],"warningList":null,"deviceName":"ASK_01_0015","equipmentName":"煤磨减速机","sort":3,"firstLevelGear":null,"secondLevelGear":null,"thirdLevelGear":null,"highSpeedBearing":null,"bearing":null,"electric":null,"temperature":21.4,"rssi":0,"battery":3.67,"temperatureHistory":[{"20:18":23.4},{"20:48":23},{"21:17":22.6},{"21:47":22.3},{"22:17":22},{"22:47":21.8},{"23:16":21.6},{"23:46":21.4}],"conkOutList":[]},{"createTime":null,"highScore":0,"lowScore":0,"startScore":0,"endScore":0,"avgScore":0,"totalScore":0,"count":0,"realTimeScore":0,"timestamp":0,"history":[],"warningList":null,"deviceName":"ASK_01_0012","equipmentName":"一号定辊减速机","sort":5,"firstLevelGear":null,"secondLevelGear":null,"thirdLevelGear":null,"highSpeedBearing":null,"bearing":null,"electric":null,"temperature":22,"rssi":26,"battery":3.63,"temperatureHistory":[{"20:10":23.6},{"20:39":23.3},{"21:09":23},{"21:39":22.7},{"22:09":22.4},{"22:38":22.3},{"23:08":22.1},{"23:38":22}],"conkOutList":[]},{"createTime":"2019-03-23T05:15:14.880+0000","highScore":0,"lowScore":0,"startScore":37,"endScore":99,"avgScore":91.25451559934318,"totalScore":111148,"count":1218,"realTimeScore":37,"timestamp":0,"history":[{"12:00":37},{"12:01":37},{"12:01":37},{"12:02":37},{"12:02":37},{"12:08":37},{"13:15":37}],"warningList":null,"deviceName":"ASK_01_0011","equipmentName":"一号动辊减速机","sort":4,"firstLevelGear":null,"secondLevelGear":null,"thirdLevelGear":null,"highSpeedBearing":null,"bearing":null,"electric":null,"temperature":null,"rssi":null,"battery":null,"temperatureHistory":[],"conkOutList":[]}]
     * id : 0632f59f06384434b1a1589520edd89e
     * proLineName : 一号生产线
     * verticalMillReducer : null
     * kilnMainReducer : null
     * coalMillReducer : null
     * firstRollerReducer : null
     * secondRollerReducer : null
     * thirdRollerReducer : null
     * fourthRollerReducer : null
     * firstCenterDrive : null
     * secondCenterDrive : null
     * sourthKilnEntry : null
     * northKilnEntry : null
     * cableZipperReducer : null
     * storageHeadLiftingReducer : null
     */

    private String createTime;
    private String highScore;
    private String lowScore;
    private String startScore;
    private String endScore;
    private String avgScore;
    private String totalScore;
    private String count;
    private String realTimeScore;
    private int sort;
    private long timestamp;
    private String id;
    private String proLineName;
    private List<Map<String,Float>> history;
    private List<Map<String,Float>> temperatureHistory;
    private List<WarningListBean> warningList;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<Map<String, Float>> getTemperatureHistory() {
        return temperatureHistory;
    }

    public void setTemperatureHistory(List<Map<String, Float>> temperatureHistory) {
        this.temperatureHistory = temperatureHistory;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHighScore() {
        return highScore;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }

    public String getLowScore() {
        return lowScore;
    }

    public void setLowScore(String lowScore) {
        this.lowScore = lowScore;
    }

    public String getStartScore() {
        return startScore;
    }

    public void setStartScore(String startScore) {
        this.startScore = startScore;
    }

    public String getEndScore() {
        return endScore;
    }

    public void setEndScore(String endScore) {
        this.endScore = endScore;
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRealTimeScore() {
        return realTimeScore;
    }

    public void setRealTimeScore(String realTimeScore) {
        this.realTimeScore = realTimeScore;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public List<Map<String, Float>> getHistory() {
        return history;
    }

    public void setHistory(List<Map<String, Float>> history) {
        this.history = history;
    }

    public List<WarningListBean> getWarningList() {
        return warningList;
    }

    public void setWarningList(List<WarningListBean> warningList) {
        this.warningList = warningList;
    }

}
