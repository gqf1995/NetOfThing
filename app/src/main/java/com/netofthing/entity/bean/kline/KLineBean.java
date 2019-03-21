package com.netofthing.entity.bean.kline;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by loro on 2017/2/8.
 */
public class KLineBean {

    Long id;

    public String date;
    public Date newDate;
    @JSONField(name = "time")
    public long timestamp;
    @JSONField(name = "o")
    public BigDecimal open;
    @JSONField(name = "c")
    public BigDecimal close;
    @JSONField(name = "h")
    public BigDecimal high;
    @JSONField(name = "l")
    public BigDecimal low;
    @JSONField(name = "vol")
    public BigDecimal volume;

    public String key;


    //    public Date getDate() {
    //        return newDate;
    //    }
    //
    //    public String getOpen() {
    //        return open.toPlainString();
    //    }
    //
    //    public String getHigh() {
    //        return high.toPlainString();
    //    }
    //
    //    public String getLow() {
    //        return low.toPlainString();
    //    }
    //
    //    public String getClose() {
    //        return close.toPlainString();
    //    }
    //
    //    public long getVolume() {
    //        return volume.longValue();
    //    }


    @Override
    public String toString() {
        return "KLineBean{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", newDate=" + newDate +
                ", timestamp=" + timestamp +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", key='" + key + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getNewDate() {
        return this.newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getOpen() {
        return this.open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return this.close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getHigh() {
        return this.high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return this.low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getVolume() {
        return this.volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
