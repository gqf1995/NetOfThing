package com.netofthing.entity.bean;

import java.math.BigDecimal;

/**
 * Created by 郭青枫 on 2019/3/21.
 */

public class ThingKlineBean {
    /**
     * id : 415264fe62eb60585f12565c46574bbe
     * targetId : 59fd9d88a4dc41bc87d417da0d90e58b
     * targetType : 1
     * targetRedisKey : ASK_01_0011
     * highScore : 77
     * lowScore : 61
     * startScore : 79
     * endScore : 10
     * avgScore : 70
     * createTime : 2019-02-18T16:00:00.000+0000
     */

    private String id;
    private String targetId;
    private int targetType;
    private String targetRedisKey;
    private BigDecimal highScore;
    private BigDecimal lowScore;
    private BigDecimal startScore;
    private BigDecimal endScore;
    private BigDecimal avgScore;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public String getTargetRedisKey() {
        return targetRedisKey;
    }

    public void setTargetRedisKey(String targetRedisKey) {
        this.targetRedisKey = targetRedisKey;
    }

    public BigDecimal getHighScore() {
        return highScore;
    }

    public void setHighScore(BigDecimal highScore) {
        this.highScore = highScore;
    }

    public BigDecimal getLowScore() {
        return lowScore;
    }

    public void setLowScore(BigDecimal lowScore) {
        this.lowScore = lowScore;
    }

    public BigDecimal getStartScore() {
        return startScore;
    }

    public void setStartScore(BigDecimal startScore) {
        this.startScore = startScore;
    }

    public BigDecimal getEndScore() {
        return endScore;
    }

    public void setEndScore(BigDecimal endScore) {
        this.endScore = endScore;
    }

    public BigDecimal getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(BigDecimal avgScore) {
        this.avgScore = avgScore;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
