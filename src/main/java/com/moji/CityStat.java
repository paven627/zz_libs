package com.moji;

import java.util.Date;

public class CityStat {
    private String uuid;
    private String adId;
    private double consumption;  // 消耗的钱数, 分
    private int impressions;
    private int clicks;

    private int cityId;
    private int positionId;
    private int saleType;
    private Date statTime;  // 聚合时间
    private String date;
    private String hour;
    private String tableName;
    private String materialId;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getStatTime() {
        return statTime;
    }

    public void setStatTime(Date statTime) {
        this.statTime = statTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getSaleType() {
        return saleType;
    }

    public void setSaleType(int saleType) {
        this.saleType = saleType;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getAdId() {

        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Override
    public String toString() {
        return "CityStat{" +
                "uuid='" + uuid + '\'' +
                ", adId='" + adId + '\'' +
                ", consumption=" + consumption +
                ", impressions=" + impressions +
                ", clicks=" + clicks +
                ", cityId=" + cityId +
                ", positionId=" + positionId +
                ", saleType=" + saleType +
                ", statTime=" + statTime +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", tableName='" + tableName + '\'' +
                ", materialId='" + materialId + '\'' +
                '}';
    }
}
